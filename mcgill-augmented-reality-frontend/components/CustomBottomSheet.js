import { View, Text, StyleSheet } from 'react-native';
import React from 'react';
import BottomSheet, {BottomSheetView} from '@gorhom/bottom-sheet';

export default function CustomBottomSheet(props) {
    const { name, address, postalCode } = props.location;
    return (
        <View style={styles.container}>
            <BottomSheet snapPoints={['15%']} enablePanDownToClose={true}>
                <BottomSheetView style={styles.sheetContainer}>
                    <View style={styles.header}>
                        <Text style={[styles.red, styles.bold]}>
                            {name}
                        </Text>
                        <Text style={styles.bold}>{name}</Text>
                    </View>
                    <View>
                        <Text>{address + ' ' + postalCode}</Text>
                    </View>
                </BottomSheetView>
            </BottomSheet>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        flex:1,
        width: "100%"
    },
    sheetContainer: {
        paddingTop: 10,
        paddingHorizontal: 10
    },
    red: {
        color: '#CD202C',
    },
    bold: {
        fontWeight: 'bold',
    },
    header: {
        flexDirection: 'row',
        justifyContent: 'space-between',
    },
});
