import { ScrollView, Button, View, Text, Pressable, StyleSheet, Image } from 'react-native'
import React, { useEffect } from 'react'
import UserImage from '../components/UserImage'
import AccountField from '../components/AccountField';
import fetchWrapper from '../api';
import {BACKEND} from '@env';

export default function AccountSettings() {
  const accountFields = ["First Name", "Last Name", "Student #", "Country", "Phone Number", "Email", "Password"];

  const tempUserData = {
    fName: 'Matthew',
    lName: 'Cabral',
    studentNumber: '261051028',
    countryCode: '1',
    phoneNumber: '5147437101',
    email: 'matthew.cabral@mail.mcgill.ca',
    password: '••••••••••••••',
    year: 2,
    faculty: 'Software Engineering'
  }

  const fullName = (tempUserData.fName ? tempUserData.fName : "User") + " " + tempUserData.lName;
  const userHeader = (tempUserData.year && ("U" + tempUserData.year)) +  " " + (tempUserData.faculty ? tempUserData.faculty : "Student");

  const uploadNewSchedule = () => {
    console.log("New Schedule Uploaded.");
  }

  const deleteAccount = () => {
    console.log("Are you sure you want to do that?");
  }

//   useEffect(() => {
//     fetchWrapper(`${BACKEND}/get-user-data`)
//   },[])

  return (
    <ScrollView style={styles.scrollViewContainer}>
        <View style={styles.container}>
            <View style={styles.accountHeaderContainer}>
                <UserImage initials="MC" />
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
                    <View style={styles.fullNameContainer}>
                        <AccountField title="First Name" value={tempUserData.fName} editable/>
                        <AccountField title="Last Name" value={tempUserData.lName} style={{marginLeft: 10}} editable/>
                    </View>
                    <AccountField title="Student Number" value={tempUserData.studentNumber} editable/>
                    <View style={styles.fullNameContainer}>
                        <AccountField title="Country Code" value={"+" + tempUserData.countryCode} style={{flex:0}} editable/>
                        <AccountField title="Phone Number" value={tempUserData.phoneNumber} style={{flex:4, marginLeft: 10}} editable/>
                    </View>
                    <AccountField title="Email" value={tempUserData.email} editable/>
                    <AccountField title="Password" value={tempUserData.password} editable/>
                </View>
                <View style={styles.dangerZoneContainer}>
                    <Text style={styles.dangerZoneText}>Danger Zone</Text>
                    <Pressable onPress={deleteAccount} style={styles.dangerZoneButton}>
                        <Text style={styles.deleteAccount}>Delete Account</Text>
                    </Pressable>
                </View>
            </View>
        </View>
    </ScrollView>
  )
}
// <a href="https://www.flaticon.com/free-icons/upload" title="upload icons">Upload icons created by Ilham Fitrotul Hayat - Flaticon</a>

const styles = StyleSheet.create({
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
    fullNameContainer: {
        flexDirection: 'row',
    },
})