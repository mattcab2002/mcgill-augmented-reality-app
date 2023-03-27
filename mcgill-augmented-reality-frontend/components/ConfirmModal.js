import {
    Text,
    StyleSheet,
    Pressable,
    TextInput,
    Modal,
    View,
    TouchableOpacity,
} from "react-native";
import React, { useState } from "react";
import fetchWrapper, { getToken } from "../api.js";
import { BACKEND, USERNAME } from "@env";

export default function ConfirmModal(props) {
    const [input, setInput] = useState("");

    const confirmHandler = async () => {
        // make request to token endpoint and check status to see if valid pass
        const token = await getToken(USERNAME, input);
        if (token != null) {
            // means valid password
            fetchWrapper(
                `${BACKEND}/user/delete-account`,
                null,
                null,
                "DELETE"
            ).then((res) => {
                //            if(res != something) {
                //                setErrorMessage and ask to try again
                //            } else
                console.log("Account deleted.");
            });
        }
        props.setVisibility(false);
    };

    return (
        <View style={styles.centeredView}>
            <Modal
                animationType="slide"
                transparent={true}
                visible={props.isVisible}
                onRequestClose={() => {
                    props.setVisibility(false);
                }}
            >
                <TouchableOpacity
                    style={styles.centeredView}
                    onPress={() => {
                        props.setVisibility(false);
                    }}
                    activeOpacity={1}
                >
                    <TouchableOpacity
                        style={styles.modalView}
                        activeOpacity={1}
                    >
                        <Text style={styles.prompt}>
                            Please re-enter your {props.text} to confirm
                        </Text>
                        <TextInput
                            style={styles.input}
                            placeholder={props.placeholder}
                            placeholderTextColor="#000"
                            secureTextEntry={
                                props.text == "password" ? true : false
                            }
                            onChangeText={(text) => {
                                setInput(text);
                            }}
                        />
                        <Pressable
                            onPress={confirmHandler}
                            style={styles.confirmButton}
                        >
                            <Text style={styles.confirmText}>Confirm</Text>
                        </Pressable>
                    </TouchableOpacity>
                </TouchableOpacity>
            </Modal>
        </View>
    );
}

const styles = StyleSheet.create({
    centeredView: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        marginTop: 22,
    },
    modalView: {
        backgroundColor: "white",
        borderRadius: 20,
        padding: 35,
        alignItems: "center",
        justifyContent: "space-between",
        shadowColor: "#000",
        shadowOffset: {
            width: 0,
            height: 2,
        },
        shadowOpacity: 0.25,
        shadowRadius: 4,
        elevation: 5,
    },
    input: {
        borderRadius: 4,
        backgroundColor: "white",
        borderColor: "#ED404C",
        borderWidth: 1,
        fontWeight: "bold",
        color: "black",
        width: "100%",
        height: 45,
        paddingLeft: 10,
        minWidth: 200,
        marginBottom: 20,
    },
    prompt: {
        marginBottom: 20,
        fontSize: 16,
    },
    confirmButton: {
        marginTop: 6,
        height: 45,
        backgroundColor: "#ED404C",
        flexDirection: "row",
        justifyContent: "center",
        alignItems: "center",
        padding: 10,
        borderRadius: 6,
    },
    confirmText: {
        color: "#FFFFFF",
        fontSize: 16,
        fontWeight: "500",
    },
});
