import { StyleSheet, Text, View } from 'react-native';

import { StatusBar } from 'expo-status-bar';
import getPath from './path-finder';
import { useEffect } from 'react';

export default function App() {

  useEffect(() => {
    getPath('45.471165, -73.650087', '45.471249, -73.648020').then(data => console.log(data));
  }, [])

  return (
    <View style={styles.container}>
      <Text>hey!</Text>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
