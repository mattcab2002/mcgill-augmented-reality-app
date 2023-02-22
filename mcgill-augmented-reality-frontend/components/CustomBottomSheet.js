import BottomSheet, { BottomSheetView } from '@gorhom/bottom-sheet';
import React, { forwardRef } from 'react';
import { StyleSheet, Text, View } from 'react-native';
import SlideButton from './CustomSlideButton';

const CustomBottomSheet = forwardRef((props, ref) => {
    const { name, address, postalCode, shortCode } = props.location;

    return (
        <BottomSheet ref={ref} snapPoints={[180]} enablePanDownToClose={true} index={-1}>
            <BottomSheetView style={styles.sheetContainer}>
                <View style={styles.header}>
                    <Text style={[styles.red, styles.bold, {fontSize: 16}]}>{name}</Text>
                    <Text style={styles.bold}>{shortCode ? ("(" + shortCode.toUpperCase() + " #)") : null}</Text>
                </View>
                <View style={styles.description}>
                    <Text>{address + ' ' + postalCode}</Text>
                </View>
                <SlideButton/>
            </BottomSheetView>
        </BottomSheet>
    );
})

const styles = StyleSheet.create({
    container: {
        flex: 1,
        width: '100%',
    },
    sheetContainer: {
        padding: 10,
        paddingHorizontal: 16,
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
        marginBottom: 10,
    },
    description: {
        marginBottom: 6
    },
});

export default CustomBottomSheet;