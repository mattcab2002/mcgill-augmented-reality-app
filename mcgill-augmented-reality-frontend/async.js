import AsyncStorage from '@react-native-async-storage/async-storage';

export const storeEmail = async (email) => {
    try {
      await AsyncStorage.setItem('@email', email)
    } catch (e) {
      console.log(e);
    }
  }

  export const storeToken = async (token) => {
    try {
      await AsyncStorage.setItem('@token', token)
    } catch (e) {
      console.log(e);
    }
  }  

export async function getEmail() {
    try {
      return await AsyncStorage.getItem('@email')
    } catch(e) {
      console.log(e);
    }
  }

export const getToken = async () => {
try {
    return await AsyncStorage.getItem('@token')
    } catch(e) {
    console.log(e);
    }
  }  