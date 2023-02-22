import React, { useState } from 'react';
import {
    Dimensions, Image, Keyboard, Pressable, ScrollView, StyleSheet, Text,
    TextInput, View
} from 'react-native';

const { width, height } = Dimensions.get('window');

export default function SearchBar(props) {
    const [showResults, setShowResults] = useState(false);
    const [searchResults, setSearchResults] = useState(
        props.locations ? props.locations : null
    );

    const onSearchResultPressed = (name, location, shortCode, index) => {
        Keyboard.dismiss();
        setShowResults(false);
        props.setDesiredLocation({
            name: name,
            location: location,
            shortCode: shortCode
        });
        props.locations.map((location, i) => {
            if (location.name == name) {
                props.markerRef.current[i].showCallout();
            }
        })
    };

    function filterSearchResults(query) {
        const newSearchResults = [];
        if (query != '') {
            props.locations.map((searchResult) => {
                if (
                    searchResult.name
                        .toLowerCase()
                        .indexOf(query.toLowerCase()) != -1
                ) {
                    newSearchResults.push(searchResult);
                }
            });
            return newSearchResults;
        } else {
            return props.locations;
        }
    }

    return (
        <View style={[styles.container, styles.border]} keyboardShouldPersistTaps='handled'>
            <View
                style={[
                    styles.searchBarContainer,
                    showResults && searchResults.length > 0
                        ? styles.searchWithResults
                        : null,
                ]}
                keyboardShouldPersistTaps='handled'
            >
                <TextInput
                    placeholder='Where To?'
                    placeholderTextColor='black'
                    style={[styles.searchField, styles.searchSpacing]}
                    onChangeText={(text) => {
                        setSearchResults(filterSearchResults(text));
                    }}
                    onFocus={() => {setShowResults(true), props.bottomSheetRef.current?.close()}}
                    onEndEditing={() => {setShowResults(false)}}
                />
                {/* <a href="https://www.flaticon.com/free-icons/search" title="search icons">Search icons created by Royyan Wijaya - Flaticon</a> */}
                {!showResults ? (
                    <Image
                        source={require('../assets/search.png')}
                        style={[styles.search, styles.searchSpacing]}
                    />
                ) : null}
            </View>
            {showResults && searchResults ? (
                <ScrollView style={styles.searchResultsContainer} keyboardShouldPersistTaps='handled'>
                    {searchResults.map((result, index) => (
                        <Pressable
                            style={styles.searchResult}
                            key={index}
                            onPress={() => {onSearchResultPressed(result.name, result.location, result.shortCode, index)}}
                        >
                            <Text>{result.name}</Text>
                            <Text>{result.shortCode ? ("(" + result.shortCode.toUpperCase() + " #)") : null}</Text>
                        </Pressable>
                    ))}
                </ScrollView>
            ) : null}
        </View>
    );
}

const styles = StyleSheet.create({
    container: {
        position: 'relative',
        top: 60,
        width: width - 20, // margin of 10 on each side
        backgroundColor: 'white',
        maxHeight: 160,
    },
    searchBarContainer: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        height: 45,
    },
    searchField: {
        width: '100%',
    },
    search: {
        width: 16,
        height: 16,
        marginLeft: -40,
    },
    searchResultsContainer: {
        flexDirection: 'column',
        paddingHorizontal: 8,
    },
    searchResult: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        marginVertical: 8,
        height: 20,
    },
    searchSpacing: {
        marginHorizontal: 8,
    },
    border: {
        borderColor: 'black',
        borderWidth: 1,
        borderRadius: 6,
    },
    searchWithResults: {
        borderBottomColor: 'black',
        borderBottomWidth: 1,
    },
});
