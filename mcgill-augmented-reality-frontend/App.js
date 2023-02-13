import React from 'react';
import {Pressable, ImageBackground, StyleSheet, Text, View} from 'react-native';

const image = {uri: 'https://www.mcgill.ca/newsroom/files/newsroom/styles/media_gallery_large/public/mcgillcampus-9728_copy.jpg?itok=vVMl7vfS'};

const App = () => (
  <View style={styles.container}>
   <ImageBackground source={image} resizeMode="cover" style={styles.image} blurRadius="2" >
      <Text style={styles.title_text}>McGill Augmented Reality App</Text>
      <View style={styles.spacing}/>
      <Pressable style={styles.button} onPress={() => {console.log("Button pressed")}}>
        <Text style={styles.btn_text}>Get Started</Text>
      </Pressable>
    </ImageBackground>
  </View>
);

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  image: {
    flex: 1,
    justifyContent: 'center',
  },
  spacing: {
    justifyContent: 'center',
    paddingVertical: 200,
  },
  title_text: {
    color: 'red',
    fontSize: 36,
    lineHeight: 60,
    fontWeight: 'bold',
    textAlign: 'center',
    backgroundColor: '#000000c0',
  },
  btn_text: {
    fontSize: 20,
    lineHeight: 21,
    fontWeight: 'bold',
    letterSpacing: 0.25,
    color: 'white',
  },
  button: {
    alignItems: 'center',
    justifyContent: 'center',
    paddingVertical: 18,
    paddingHorizontal: 32,
    borderRadius: 4,
    elevation: 3,
    backgroundColor: 'red',
  },
});

export default App;