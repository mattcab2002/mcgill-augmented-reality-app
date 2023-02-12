import { View, Text, StyleSheet } from 'react-native';
import React from 'react';
import BottomSheet from '@gorhom/bottom-sheet';

export default function CustomBottomSheet(props) {
    const { name, address, postalCode } = props.location;
    return (
        <View style={styles.container}>
            <BottomSheet snapPoints={['15%']}>
                <View style={styles.sheetContainer}>
                    <View style={styles.header}>
                        <Text style={[styles.red, styles.bold]}>
                            {name}
                        </Text>
                        <Text style={styles.bold}>{name}</Text>
                    </View>
                    <View>
                        <Text>{address + ' ' + postalCode}</Text>
                    </View>
                </View>
            </BottomSheet>
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        width: '100%',
        height: '100%',
    },
    sheetContainer: {
        padding: 10,
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
