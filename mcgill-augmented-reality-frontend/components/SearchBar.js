import {
    View,
    Text,
    TextInput,
    Image,
    StyleSheet,
    Dimensions,
} from 'react-native';
import React from 'react';

const { width, height } = Dimensions.get('window');

export default function SearchBar() {
    return (
        <View style={styles.container}>
            <TextInput placeholder='Where To?' placeholderTextColor='black' style={styles.searchField}/>
            {/* <a href="https://www.flaticon.com/free-icons/search" title="search icons">Search icons created by Royyan Wijaya - Flaticon</a> */}
            <Image
                source={require('../assets/search.png')}
                style={styles.search}
            />
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        position: 'relative',
        top: 20,
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        width: width - 20, // margin of 10 on each side
        height: 45,
        borderColor: 'black',
        borderWidth: 1,
        borderRadius: 6,
        backgroundColor: 'white',
        paddingHorizontal: 8,
    },
    searchField: {
        width: '100%',
    },
    search: {
        width: 16,
        height: 16,
        marginLeft: -16
    },
});
