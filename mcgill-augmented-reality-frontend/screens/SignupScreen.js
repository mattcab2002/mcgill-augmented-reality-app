import { React, useState } from 'react';
import { StyleSheet, View, TouchableOpacity, TouchableWithoutFeedback, Text, TextInput, Keyboard } from 'react-native';
import { registerUser } from '../api';

function SignupScreen({ navigation }) {

    const [name, onChangeName] = useState('');
    const [email, onChangeEmail] = useState('');
    const [password, onChangePassword] = useState('');
    const [isErrorShow, setIsErrowShow] = useState(false);

    function handlePress() {
        let valid = email.includes('@') && email.includes('.');

        if (valid) {
            registerUser(email, password)
            .then(res => {
                if (!res.ok) {
                    console.log(res.error())
                }
            });
            // navigation.navigate('nextScreenName');
        } else {
            setIsErrowShow(true);
        }
        
    }

    return (
        <TouchableWithoutFeedback onPress={() => Keyboard.dismiss()}>
            <View style={styles.container}>
                <Text style={styles.signupText}>Signup</Text>
                <View style={styles.spaceSmall}/>
                <Text style={styles.infoText}>
                    Please fill out the following details to create an account
                </Text>
                <View style={styles.spaceLarge}/>
                <TextInput 
                    style={styles.input} 
                    onChangeText={onChangeName}
                    value={name}
                    placeholder='Full Name' 
                />
                <View style={styles.spaceSmall}/>
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
                    secureTextEntry={true}
                />
                <View style={styles.spaceSmall}/>
                <Text style={{
                    color: 'red',
                    fontSize: 15,
                    textAlign: 'center',
                    width: '80%',
                    opacity: isErrorShow ? 1 : 0
                }}>
                    Please enter a valid email.
                </Text>
                <View style={styles.spaceLarge}/>
                <TouchableOpacity style={styles.button} onPress={handlePress}>
                    <Text style={styles.buttonText}>Create Account</Text>
                </TouchableOpacity>
                <View style={styles.spaceSmall}/>
                <Text style={styles.infoText}>
                    Already have an account?
                </Text>
                <Text style={styles.loginText} onPress={() => navigation.navigate('Login')}>Login</Text>
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
    signupText: {
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
    loginText: {
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

export default SignupScreen;