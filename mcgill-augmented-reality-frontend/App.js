import React from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import HomeScreen from './screens/HomeScreen';
import LoginScreen from './screens/LoginScreen';
import SignupScreen from './screens/SignupScreen';
import NavigationBar from './components/NavigationBar';
import { NavigationContainer } from '@react-navigation/native';

const Stack = createNativeStackNavigator();

const App = () => (
  
  <NavigationContainer>
    <Stack.Navigator>
      <Stack.Screen 
        name="Home" 
        component={HomeScreen}
        options={{headerShown: false}}
      />
      <Stack.Screen 
        name="Login" 
        component={LoginScreen}
        options={{headerShown: false}}
      />
      <Stack.Screen 
        name="Signup" 
        component={SignupScreen}
        options={{headerShown: false}}
      />
    </Stack.Navigator>
    <NavigationBar />
  </NavigationContainer>
  
);

export default App;