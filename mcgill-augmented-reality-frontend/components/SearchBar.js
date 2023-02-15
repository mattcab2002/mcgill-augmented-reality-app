import {
    View,
    Text,
    TextInput,
    Image,
    StyleSheet,
    Dimensions,
    ScrollView,
    Pressable
} from 'react-native';
import React, { useState } from 'react';

const { width, height } = Dimensions.get('window');

export default function SearchBar(props) {
    const [showResults, setShowResults] = useState(false);
    const [resultPressed, setResultPressed] = useState(false);
    const [searchResults, setSearchResults] = useState(
        props.locations ? props.locations : null
    );
    const [query, setQuery] = useState('');

    return (
        <View style={styles.container}>
            <View style={styles.searchBarContainer}>
                <TextInput
                    placeholder='Where To?'
                    placeholderTextColor='black'
                    value={resultPressed ? query : null}
                    style={[styles.searchField, styles.searchSpacing]}
                    onChangeText={(text) => {
                        setQuery(text);
                    }}
                    onEndEditing={() => {
                        query == '' && setShowResults(false);
                    }}
                    onFocus={() => setShowResults(true)}
                />
                {/* <a href="https://www.flaticon.com/free-icons/search" title="search icons">Search icons created by Royyan Wijaya - Flaticon</a> */}
                {!query ? (
                    <Image
                        source={require('../assets/search.png')}
                        style={[styles.search, styles.searchSpacing]}
                    />
                ) : null}
            </View>
            {showResults && searchResults ? (
                <ScrollView style={styles.searchResultsContainer}>
                    {searchResults.map((result) => (
                        <Pressable style={styles.searchResult} key={result.name} onPress={() => {setQuery(result.name), setShowResults(false), setResultPressed(true)}}>
                            <Text>{result.name}</Text>
                            <Text>{result.location.postalCode}</Text>
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
        maxHeight: 150,
    },
    searchBarContainer: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        alignItems: 'center',
        borderColor: 'black',
        borderWidth: 1,
        borderRadius: 6,
        backgroundColor: 'white',
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
        backgroundColor: 'white',
        borderColor: 'black',
        borderWidth: 1,
        height: '100%',
        flexDirection: 'column',
        paddingHorizontal: 8,
        borderRadius: 6,
    },
    searchResult: {
        flexDirection: 'row',
        justifyContent: 'space-between',
        marginVertical: 4,
    },
    searchSpacing: {
        marginHorizontal: 8,
    },
});
