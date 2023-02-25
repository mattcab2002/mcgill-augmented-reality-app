import { ScrollView, Button, View, Text, Pressable, StyleSheet, Image, KeyboardAvoidingView, Platform } from 'react-native'
import React, { useState, useEffect } from 'react'
import UserImage from '../components/UserImage'
import AccountField from '../components/AccountField';
import fetchWrapper from '../api';
import {BACKEND} from '@env';
import * as ImagePicker from 'expo-image-picker';
import ConfirmModal from '../components/ConfirmModal';

export default function AccountSettings() {
  const placeHolderPassword = "••••••••••••";

  const [image, setImage] = useState(null);
  const [modalVisible, setModalVisible] = useState(false);
  const [userInfo, setUserInfo] = useState({
    firstName: null,
    lastName: null,
    studentNumber: null,
    email: null,
    username: null,
    countryCode: null,
    phoneNumber: null,
    faculty: null,
    year: null,
    password: placeHolderPassword
  });

  useEffect(() => {
        fetchWrapper(`${BACKEND}/user-info/all-user-info`).then(
            (info) => {
                const newUserInfo = {};
                Object.keys(userInfo).forEach((key) => {
                    if(userInfo[key] == null) {
                        newUserInfo[key] = info[key];
                     } else {
                        newUserInfo[key] = userInfo[key];
                     }
                })
                newUserInfo["username"] = info.user.username;
                setUserInfo(newUserInfo);
            }
        );
  },[])

  const initials = (userInfo.firstName && userInfo.lastName) ? ((userInfo.firstName && typeof userInfo.firstName === 'string') ? userInfo.firstName.charAt(0) : "") + ((userInfo.lastName && typeof userInfo.lastName === 'string') ? userInfo.lastName.charAt(0) : "") : "U";
  const fullName = (userInfo.firstName ? (userInfo.firstName + " ") : "User ") + (userInfo.lastName ? userInfo.lastName : "");
  const userHeader = (userInfo.year ? ("U" + userInfo.year + " ") : "") + (userInfo.faculty ? userInfo.faculty : "Student");

  const uploadNewSchedule = async () => {
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

  const deleteAccount = () => {
    setModalVisible(true);
  }


  return (
    <KeyboardAvoidingView behavior={Platform.OS === 'ios' ? 'position' : 'height'} style={styles.avoidingViewContainer} >
        <ScrollView style={styles.scrollViewContainer}>
            <View style={styles.container}>
                <View style={styles.accountHeaderContainer}>
                    <UserImage initials={initials} />
                    <View style={styles.accountHeaderText}>
                        <Text style={styles.headerText}>{fullName}</Text>
                        <Text style={styles.greyText}>{userHeader}</Text>
                        <Text style={styles.greyText}>McGill University</Text>
                    </View>
                </View>
                <View style={styles.userFieldsContainer}>
                    <Pressable onPress={uploadNewSchedule} style={styles.uploadScheduleButton}>
                        <Text style={styles.uploadText}>Upload Schedule</Text>
                        <Image source={require('../assets/upload.png')} style={styles.uploadIcon} />
                    </Pressable>
                    <View style={styles.inputFieldContainer}>
                        {(userInfo.firstName && userInfo.lastName) ? 
                        <View style={styles.splitContainer}>
                            <AccountField title="First Name" value={userInfo.firstName}/>
                            <AccountField title="Last Name" value={userInfo.lastName} style={{marginLeft: 10}}/>
                        </View>
                        : null}
                        {userInfo.studentNumber ? <AccountField title="Student Number" value={String(userInfo.studentNumber)}/> : null}
                        {(userInfo.countryCode && userInfo.phoneNumber) ? 
                        <View style={styles.splitContainer}>
                            <AccountField title="Country Code" value={"+" + userInfo.countryCode} style={{flex:0}}/>
                            <AccountField title="Phone Number" value={userInfo.phoneNumber} style={{flex:4, marginLeft: 10}}/>
                        </View>
                        : null}
                        {userInfo.email ? <AccountField title="Email" value={userInfo.email}/> : null}
                        {userInfo.username ? <AccountField title="Username" value={userInfo.username} /> : null}
                        {userInfo.password ? <AccountField title="Password" value={userInfo.password} /> : null}
                    </View>
                    <View style={styles.dangerZoneContainer}>
                        <Text style={styles.dangerZoneText}>Danger Zone</Text>
                        <Pressable onPress={deleteAccount} style={styles.dangerZoneButton}>
                            <Text style={styles.deleteAccount}>Delete Account</Text>
                        </Pressable>
                    </View>
                </View>
            </View>
            <ConfirmModal isVisible={modalVisible} text="password" placeholder={placeHolderPassword} setVisibility={(visibility) => {setModalVisible(visibility)}} />
        </ScrollView>
    </KeyboardAvoidingView>
  )
}
// <a href="https://www.flaticon.com/free-icons/upload" title="upload icons">Upload icons created by Ilham Fitrotul Hayat - Flaticon</a>

const styles = StyleSheet.create({
    avoidingViewContainer: {
        flex: 1,
    },
    container: {
        paddingHorizontal: 20,
        paddingTop: 60,
        paddingBottom: 40
    },
    accountHeaderContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        marginHorizontal: 'auto',
        justifyContent: 'center',
        marginTop: 30
    },
    accountHeaderText: {
        flexDirection: 'column',
        marginLeft: 10,
        height: 60,
        justifyContent: 'space-between',
    },
    headerText: {
        color: 'black',
        fontSize: 20
    }, 
    greyText: {
        color: '#5A5A5A'
    },
    dangerZoneText:{
        color: "#ED404C",
        fontWeight: "bold"
    },
    dangerZoneContainer: {
        flexDirection: 'column',
        justifyContent: 'center',
        alignItems: 'flex-start',
    },
    dangerZoneButton: {
        marginTop: 6,
        height: 45,
        backgroundColor: 'white',
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        padding: 10, 
        borderRadius: 6,
        borderWidth: 1,
        borderColor: "#ED404C",
        width: '100%'
    },
    deleteAccount: {
        color: '#ED404C',
        fontSize: 16,
        fontWeight: '500',
    },
    uploadScheduleButton: {
        height: 45,
        backgroundColor: '#CD202C',
        flexDirection: 'row',
        justifyContent: 'center',
        alignItems: 'center',
        padding: 10, 
        borderRadius: 6,
    },
    uploadText: {
        color: 'white',
        fontSize: 16,
        fontWeight: '500',
        marginLeft: 'auto'
    },
    uploadIcon: {
        width: 20,
        height: 20,
        tintColor: 'white',
        marginLeft: 'auto'
    },
    userFieldsContainer: {
        marginTop: 40
    },
    inputFieldContainer: {
        marginVertical: 12
    }, 
    splitContainer: {
        flexDirection: 'row',
    },
})
