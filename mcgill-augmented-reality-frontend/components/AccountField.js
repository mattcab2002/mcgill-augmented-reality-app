import { View, Text, TextInput, StyleSheet, Image } from 'react-native'
import React from 'react'
import fetchWrapper from '../api'
import {BACKEND} from '@env';

export default function AccountField(props) {
    const updateData = (ref) => {
        // use the ref of the input to get the value then make the api request to update
        // fetchWrapper(`${BACKEND}/route`)
    }

  return (
    <View style={[styles.container, props.style]}>
        <Text style={styles.textInputTitle}>{props.title ? props.title : "Input Field"}</Text>
        <TextInput style={styles.textInput} editable={props.editable ? true : false} defaultValue={props.value} onEndEditing={updateData} />
    </View>
  )
}

const styles = StyleSheet.create({
    container: {
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'flex-start',
        marginVertical: 4,
        flex:1
    },
    textInputTitle: {
        color: '#ABABAB',
        fontWeight: 'bold',
    },
    textInput: {
        borderRadius: 4,
        backgroundColor: 'white',
        borderColor: '#ABABAB',
        borderWidth: 1,
        fontWeight: 'bold',
        color: 'black',
        width: "100%",
        height: 45,
        paddingLeft: 10,
        marginTop: 6,
    },
})