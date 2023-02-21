import React from 'react';
import { StyleSheet, View, TouchableOpacity, TouchableWithoutFeedback, Text, TextInput, Keyboard } from 'react-native';

function LoginScreen({ navigation }) {

    const [email, onChangeEmail] = React.useState('');
    const [password, onChangePassword] = React.useState('');

    function handlePress() {
        // TODO: call backend API for login in
        console.log('Login pressed');
        console.log('Email: ' + email);
        console.log('Password: ' + password);
        // TODO: switch to next screen
        // navigation.navigate('nextScreenName');
    }

    return (
        <TouchableWithoutFeedback onPress={() => Keyboard.dismiss()}>
            <View style={styles.container}>
                <Text style={styles.loginText}>Login</Text>
                <View style={styles.spaceSmall}/>
                <Text style={styles.infoText}>
                    Please login to continue using the app
                </Text>
                <View style={styles.spaceLarge}/>
                <TextInput 
                    style={styles.input} 
                    onChangeText={onChangeEmail} 
                    value={email}
                    placeholder='Email' 
                />
                <View style={styles.spaceSmall}/>
                <TextInput 
                    style={styles.input} 
                    onChangeText={onChangePassword}
                    value={password} 
                    placeholder='Password'
                />
                <View style={styles.spaceLarge}/>
                <TouchableOpacity style={styles.button} onPress={handlePress}>
                    <Text style={styles.buttonText}>Login</Text>
                </TouchableOpacity>
                <View style={styles.spaceSmall}/>
                <Text style={styles.infoText}>
                    Don't have an account?
                </Text>
                <Text style={styles.signupText} onPress={() => navigation.navigate('Signup')}>Sign Up</Text>
            </View>
        </TouchableWithoutFeedback>
    );
}

const styles = StyleSheet.create({
    button: {
        width: '80%',
        height: 50,
        backgroundColor: "#cf4037",
        justifyContent: 'center',
        alignItems: 'center'
    },
    container: {
        flex: 1,
        backgroundColor: 'white',
        justifyContent: 'center',
        alignItems: 'center'
    },
    input: {
        width: '80%',
        height: 50,
        borderColor: 'darkgrey',
        borderWidth: 2,
        padding: 10
    },
    buttonText: {
        color: 'white',
        fontSize: 20
    },
    loginText: {
        color: 'black',
        fontSize: 30,
        textAlign: 'center'
    },
    infoText: {
        color: 'black',
        fontSize: 15,
        textAlign: 'center',
        width: '80%'
    },
    signupText: {
        color: 'red',
        fontSize: 15,
        textDecorationLine: 'underline',
        textAlign: 'center'
    },
    spaceSmall: {
        width: '100%',
        height: 15
    },
    spaceLarge: {
        width: '100%',
        height: 80
    }
})

export default LoginScreen;