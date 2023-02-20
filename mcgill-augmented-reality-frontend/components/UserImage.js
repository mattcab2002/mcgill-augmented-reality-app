import { View, Pressable, Image, StyleSheet, Text } from 'react-native'
import React, {useState} from 'react'
import * as ImagePicker from 'expo-image-picker';

export default function UserImage(props) {
    const [image, setImage] = useState(null);

  const uploadUserImage = async () => {
        // No permissions request is necessary for launching the image library
        let result = await ImagePicker.launchImageLibraryAsync({
          mediaTypes: ImagePicker.MediaTypeOptions.All,
          allowsEditing: true,
          aspect: [4, 3],
          quality: 1,
        });
    
        console.log(result);
    
        if (!result.canceled) {
          setImage(result.assets[0].uri);
        }
    }

  return (
    <View>
        {props.initials ? (
            <View style={styles.userImage}> 
                <Text style={styles.initials}>{props.initials}</Text> 
            </View>) : 
            (props.imageSource ? 
            (<Image source={props.imageSource} style={styles.userImage} />) : 
            (<View style={styles.userImage} />))
        }
        <Pressable style={styles.cameraIconBackground} onPress={uploadUserImage}>
            <Image source={require('../assets/camera.png')} style={styles.camera}/>
        </Pressable>
    </View>
  )
}
{/* <a href="https://www.flaticon.com/free-icons/hobbies-and-free-time" title="hobbies and free time icons">Hobbies and free time icons created by Fabien Bienefeld - Flaticon</a> */}

const styles = StyleSheet.create({
    userImage: {
        borderRadius: 100,
        backgroundColor: '#D9D9D9',
        width: 100,
        height: 100,
        justifyContent: 'center',
        alignItems: 'center',
    },
    camera: {
        width: 25,
        height: 25,
    },
    cameraIconBackground: {
        backgroundColor: 'white',
        borderRadius: 100,
        width: 40,
        height: 40,
        justifyContent: 'center',
        alignItems: 'center',
        opacity: 0.6,
        position: 'absolute',
        bottom: -5,
        right: -5
    },
    initials: {
        fontSize: 40,
        color: 'grey'
    }
})