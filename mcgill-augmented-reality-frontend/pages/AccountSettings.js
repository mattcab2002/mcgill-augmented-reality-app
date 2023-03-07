import {
    ScrollView,
    View,
    Text,
    Pressable,
    StyleSheet,
    Image,
    KeyboardAvoidingView,
    Platform,
    Alert,
} from "react-native";
import React, { useState, useEffect } from "react";
import UserImage from "../components/UserImage";
import AccountField from "../components/AccountField";
import fetchWrapper from "../api";
import { BACKEND } from "@env";
import * as ImagePicker from "expo-image-picker";
import ConfirmModal from "../components/ConfirmModal";
import ScheduleModal from "../components/ScheduleModal";

export default function AccountSettings() {
    const placeHolderPassword = "••••••••••••";

    const [schedule, setSchedule] = useState({ uri: null, name: null });
    const [profilePicture, setProfilePicture] = useState({
        uri: null,
        name: null,
    });
    const [modalVisible, setModalVisible] = useState(false);
    const [scheduleVisible, setScheduleVisible] = useState(false);
    const [userInfo, setUserInfo] = useState({
        firstName: null,
        lastName: null,
        studentNumber: null,
        email: null,
        username: null,
        countryCode: null,
        phoneNumber: null,
        faculty: null,
        year: null,
        password: placeHolderPassword,
    });
    const createImageSizeTooLargeAlert = () => {
        return Alert.alert("The image you tried to upload is too large. Please reduce it's size and try again.");
    }

    useEffect(() => {
        fetchWrapper(`${BACKEND}/user-info/`, {
            "Content-Type": "application/json",
        }).then((info) => {
            const newUserInfo = {};
            Object.keys(userInfo).forEach((key) => {
                if (userInfo[key] == null) {
                    newUserInfo[key] = info[key];
                } else {
                    newUserInfo[key] = userInfo[key];
                }
            });
            newUserInfo["username"] = info.user?.username;
            setUserInfo(newUserInfo);
        });

        fetchWrapper(`${BACKEND}/image/profile`)
            .then((data) => {
                return data.blob();
            })
            .then((imageBlob) => {
                const fileReaderInstance = new FileReader();
                fileReaderInstance.readAsDataURL(imageBlob);
                fileReaderInstance.onload = () => {
                    setProfilePicture({
                        uri: fileReaderInstance.result,
                        name: imageBlob._data.name,
                    });
                };
            });

        fetchWrapper(`${BACKEND}/image/schedule`)
            .then((data) => {
                return data.blob();
            })
            .then((imageBlob) => {
                const fileReaderInstance = new FileReader();
                fileReaderInstance.readAsDataURL(imageBlob);
                fileReaderInstance.onload = () => {
                    setSchedule({
                        uri: fileReaderInstance.result,
                        name: imageBlob._data.name,
                    });
                };
            });
    }, []);

    const initials =
        userInfo.firstName && userInfo.lastName
            ? (userInfo.firstName && typeof userInfo.firstName === "string"
                  ? userInfo.firstName.charAt(0)
                  : "") +
              (userInfo.lastName && typeof userInfo.lastName === "string"
                  ? userInfo.lastName.charAt(0)
                  : "")
            : "U";

    const fullName =
        (userInfo.firstName ? userInfo.firstName + " " : "User ") +
        (userInfo.lastName ? userInfo.lastName : "");

    const userHeader =
        (userInfo.year ? "U" + userInfo.year + " " : "") +
        (userInfo.faculty ? userInfo.faculty : "Student");

    const uploadNewSchedule = async () => {
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
            const fileName = "schedule_" + userInfo.username + "." + extension;

            imageData.append("image", {
                uri: upload.uri,
                type: `image/${extension}`,
                name: fileName,
            });

            fetchWrapper(
                `${BACKEND}/image/schedule`,
                { "Content-Type": "multipart/form-data" },
                "PUT",
                imageData
            ).then((res) => {
                if(res) {
                    if (res.ok) {
                        console.log("Schedule uploaded.");
                        setSchedule({ uri: upload.uri, name: fileName });
                    } else {
                        console.log("Failed to upload schedule.");
                    }
                } else {
                    // can assume file too large
                    createImageSizeTooLargeAlert();
                }
            });
        }
    };

    const showSchedule = () => {
        setScheduleVisible(true);
    };

    const deleteAccount = () => {
        setModalVisible(true);
    };

    return (
        <KeyboardAvoidingView
            behavior={Platform.OS === "ios" ? "position" : "height"}
            style={styles.avoidingViewContainer}
        >
            <ScrollView style={styles.scrollViewContainer}>
                <View style={styles.container}>
                    <View style={styles.accountHeaderContainer}>
                        <UserImage
                            initials={!profilePicture.uri ? initials : null}
                            username={userInfo.username}
                            setProfilePicture={(image) => {
                                setProfilePicture(image);
                            }}
                            imageSource={profilePicture.uri}
                            imageSizeAlertHandler={() => createImageSizeTooLargeAlert()}
                        />
                        <View style={styles.accountHeaderText}>
                            <Text style={styles.headerText}>{fullName}</Text>
                            <Text style={styles.greyText}>{userHeader}</Text>
                            <Text style={styles.greyText}>
                                McGill University
                            </Text>
                        </View>
                    </View>
                    <View style={styles.userFieldsContainer}>
                        <Pressable
                            onPress={uploadNewSchedule}
                            style={styles.uploadScheduleButton}
                        >
                            <Text style={styles.uploadText}>
                                Upload Schedule
                            </Text>
                            <Image
                                source={require("../assets/upload.png")}
                                style={styles.uploadIcon}
                            />
                        </Pressable>
                        {schedule.name ? (
                            <View
                                style={{ marginTop: 10, flexDirection: "row" }}
                            >
                                <Text>Current Schedule: </Text>
                                <Pressable onPress={showSchedule}>
                                    <Text
                                        style={{
                                            textDecorationLine: "underline",
                                            color: "#ED404C",
                                        }}
                                    >
                                        {schedule.name}
                                    </Text>
                                </Pressable>
                            </View>
                        ) : null}
                        <View style={styles.inputFieldContainer}>
                            {userInfo.firstName && userInfo.lastName ? (
                                <View style={styles.splitContainer}>
                                    <AccountField
                                        title="First Name"
                                        value={userInfo.firstName}
                                    />
                                    <AccountField
                                        title="Last Name"
                                        value={userInfo.lastName}
                                        style={{ marginLeft: 10 }}
                                    />
                                </View>
                            ) : null}
                            {userInfo.studentNumber ? (
                                <AccountField
                                    title="Student Number"
                                    value={String(userInfo.studentNumber)}
                                />
                            ) : null}
                            {userInfo.countryCode && userInfo.phoneNumber ? (
                                <View style={styles.splitContainer}>
                                    <AccountField
                                        title="Country Code"
                                        value={"+" + userInfo.countryCode}
                                        style={{ flex: 0 }}
                                    />
                                    <AccountField
                                        title="Phone Number"
                                        value={userInfo.phoneNumber}
                                        style={{ flex: 4, marginLeft: 10 }}
                                    />
                                </View>
                            ) : null}
                            {userInfo.email ? (
                                <AccountField
                                    title="Email"
                                    value={userInfo.email}
                                />
                            ) : null}
                            {userInfo.username ? (
                                <AccountField
                                    title="Username"
                                    value={userInfo.username}
                                />
                            ) : null}
                            {userInfo.password ? (
                                <AccountField
                                    title="Password"
                                    value={userInfo.password}
                                />
                            ) : null}
                        </View>
                        <View style={styles.dangerZoneContainer}>
                            <Text style={styles.dangerZoneText}>
                                Danger Zone
                            </Text>
                            <Pressable
                                onPress={deleteAccount}
                                style={styles.dangerZoneButton}
                            >
                                <Text style={styles.deleteAccount}>
                                    Delete Account
                                </Text>
                            </Pressable>
                        </View>
                    </View>
                </View>
                <ConfirmModal
                    isVisible={modalVisible}
                    text="password"
                    placeholder={placeHolderPassword}
                    setVisibility={(visibility) => {
                        setModalVisible(visibility);
                    }}
                />
                {schedule.uri ? (
                    <ScheduleModal
                        isVisible={scheduleVisible}
                        imageSource={schedule.uri}
                        setVisibility={(visibility) => {
                            setScheduleVisible(visibility);
                        }}
                    />
                ) : null}
            </ScrollView>
        </KeyboardAvoidingView>
    );
}
// <a href="https://www.flaticon.com/free-icons/upload" title="upload icons">Upload icons created by Ilham Fitrotul Hayat - Flaticon</a>

const styles = StyleSheet.create({
    avoidingViewContainer: {
        flex: 1,
    },
    container: {
        paddingHorizontal: 20,
        paddingTop: 60,
        paddingBottom: 40,
    },
    accountHeaderContainer: {
        flexDirection: "row",
        alignItems: "center",
        marginHorizontal: "auto",
        justifyContent: "center",
        marginTop: 30,
    },
    accountHeaderText: {
        flexDirection: "column",
        marginLeft: 10,
        height: 60,
        justifyContent: "space-between",
    },
    headerText: {
        color: "black",
        fontSize: 20,
    },
    greyText: {
        color: "#5A5A5A",
    },
    dangerZoneText: {
        color: "#ED404C",
        fontWeight: "bold",
    },
    dangerZoneContainer: {
        flexDirection: "column",
        justifyContent: "center",
        alignItems: "flex-start",
    },
    dangerZoneButton: {
        marginTop: 6,
        height: 45,
        backgroundColor: "white",
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
        padding: 10,
        borderRadius: 6,
        borderWidth: 1,
        borderColor: "#ED404C",
        width: "100%",
    },
    deleteAccount: {
        color: "#ED404C",
        fontSize: 16,
        fontWeight: "500",
    },
    uploadScheduleButton: {
        height: 45,
        backgroundColor: "#CD202C",
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
        padding: 10,
        borderRadius: 6,
    },
    uploadText: {
        color: "white",
        fontSize: 16,
        fontWeight: "500",
        marginLeft: "auto",
    },
    uploadIcon: {
        width: 20,
        height: 20,
        tintColor: "white",
        marginLeft: "auto",
    },
    userFieldsContainer: {
        marginTop: 40,
    },
    inputFieldContainer: {
        marginVertical: 12,
    },
    splitContainer: {
        flexDirection: "row",
    },
});
