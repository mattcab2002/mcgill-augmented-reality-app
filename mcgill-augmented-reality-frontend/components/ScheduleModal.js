import { StyleSheet, Modal, View, TouchableOpacity, Image } from "react-native";
import React from "react";

export default function ScheduleModal(props) {
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
                        <Image
                            source={{ uri: props.imageSource }}
                            style={styles.scheduleImage}
                        />
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
    scheduleImage: {
        width: 300,
        height: 200,
    },
});
