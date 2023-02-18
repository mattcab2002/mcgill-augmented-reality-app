import { View, Text, StyleSheet, Image } from 'react-native';
import React, { forwardRef } from 'react';
import BottomSheet, { BottomSheetView } from '@gorhom/bottom-sheet';
import SlideButton from 'rn-slide-button';

const CustomBottomSheet = forwardRef((props, ref) => {
    const { name, address, postalCode } = props.location;

    const retrieveIFrame = () => {
        console.log("Retrieving IFrame...");
    }

    return (
        <BottomSheet ref={ref} snapPoints={['20%']} enablePanDownToClose={true} index={-1}>
            <BottomSheetView style={styles.sheetContainer}>
                <View style={styles.header}>
                    <Text style={[styles.red, styles.bold, {fontSize: 16}]}>{name}</Text>
                    <Text style={styles.bold}>{name}</Text>
                </View>
                <View style={styles.description}>
                    <Text>{address + ' ' + postalCode}</Text>
                </View>
                <SlideButton title="Visualize Path" borderRadius={6} height={40} onReachToEnd={retrieveIFrame} containerStyle={{backgroundColor: '#CD202C'}} thumbStyle={{borderRadius: 6, width: 44}} underlayStyle={{backgroundColor: '#CD202C'}} icon={<Image style={{width: 24, height: 24, tintColor: "#CD202C"}} source={require('../assets/arrow-right.png')} />}/>
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
        marginBottom: 10
    },
    button: {
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#CD202C',
        padding: 12,
        borderRadius: 6,
        borderColor: 'black',
    }
});

export default CustomBottomSheet;