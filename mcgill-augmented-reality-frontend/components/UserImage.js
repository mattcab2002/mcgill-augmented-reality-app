import { BACKEND } from "@env";
import * as ImagePicker from "expo-image-picker";
import React from "react";
import { Image, Pressable, StyleSheet, Text, View } from "react-native";
import fetchWrapper from "../api";

export default function UserImage(props) {
    const uploadUserImage = async () => {
        // No permissions request is necessary for launching the image library
        let result = await ImagePicker.launchImageLibraryAsync({
            mediaTypes: ImagePicker.MediaTypeOptions.All,
            allowsEditing: true,
            aspect: [4, 3],
            quality: 1,
        });

        console.log(result);

        if (!result.canceled) {
            const upload = result.assets[0];
            const imageData = new FormData();
            const extension = upload.uri.substring(
                upload.uri.lastIndexOf(".") + 1
            );
            const fileName = "profile_" + props.username + "." + extension;

            imageData.append("image", {
                uri: upload.uri,
                type: `image/${extension}`,
                name: fileName,
            });

            fetchWrapper(
                `${BACKEND}/image/profile`,
                { "Content-Type": "multipart/form-data" },
                "PUT",
                imageData
            ).then((res) => {
                if(res) {
                    if (res.ok) {
                        console.log("Profile picture uploaded.");
                        props.setProfilePicture({
                            uri: upload.uri,
                            name: fileName,
                        });
                    } else {
                        console.log("Failed to upload profile picture.");
                    }
                } else {
                    // assume image too large
                    props.imageSizeAlertHandler()
                }
            });
        }
    };

    return (
        <View>
            {props.initials ? (
                <View style={styles.userImage}>
                    <Text style={styles.initials}>{props.initials}</Text>
                </View>
            ) : props.imageSource ? (
                <Image
                    source={{ uri: props.imageSource }}
                    style={styles.userImage}
                />
            ) : (
                <View style={styles.userImage} />
            )}
            <Pressable
                style={styles.cameraIconBackground}
                onPress={uploadUserImage}
            >
                <Image
                    source={require("../assets/camera.png")}
                    style={styles.camera}
                />
            </Pressable>
        </View>
    );
}
{
    /* <a href="https://www.flaticon.com/free-icons/hobbies-and-free-time" title="hobbies and free time icons">Hobbies and free time icons created by Fabien Bienefeld - Flaticon</a> */
}

const styles = StyleSheet.create({
    userImage: {
        borderRadius: 100,
        backgroundColor: "#D9D9D9",
        width: 100,
        height: 100,
        justifyContent: "center",
        alignItems: "center",
    },
    camera: {
        width: 25,
        height: 25,
    },
    cameraIconBackground: {
        backgroundColor: "white",
        borderRadius: 100,
        width: 40,
        height: 40,
        justifyContent: "center",
        alignItems: "center",
        opacity: 0.6,
        position: "absolute",
        bottom: -5,
        right: -5,
    },
    initials: {
        fontSize: 40,
        color: "grey",
    },
});
