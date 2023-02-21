import React from 'react';
import { ImageBackground, StyleSheet, View, TouchableOpacity, Image, Text } from 'react-native';

const image = {uri: 'https://wallpapercave.com/wp/wp10982822.jpg'};

function HomeScreen({ navigation }) {
    return (
        <ImageBackground source={image} resizeMode="cover" style={styles.bgImage} blurRadius={0}>
            <Image source={require('../assets/mcgill-icon.png')} style={styles.logo}/>
            <Text style={styles.title}>AUGMENTED REALITY</Text>
            <TouchableOpacity style={styles.button} onPress={() => navigation.navigate('Login')}>
                <Text style={styles.started}>Get Started</Text>
            </TouchableOpacity>
        </ImageBackground>
    );
}

const styles = StyleSheet.create({
    title: {
        color: '#cf4037',
        fontSize: 37,
        fontWeight: 'bold',
        letterSpacing: 2,
        textAlign: 'center',
        position: 'absolute',
        top: 220
    },
    bgImage: {
        flex: 1,
        justifyContent: "flex-end",
        alignItems: "center"
    },
    button: {
        width: '100%',
        height: 80,
        backgroundColor: "red",
        justifyContent: 'center',
        alignItems: 'center'
    },
    logo: {
        backgroundColor: "#00000000",
        flex: 1,
        width: '70%',
        height: '13%',
        resizeMode: 'contain',
        position: 'absolute',
        top: 100,
    },
    started: {
        color: 'white',
        fontSize: 23,
        fontFamily: 'AvenirNext-Medium',
        letterSpacing: 2
    }
})

export default HomeScreen;