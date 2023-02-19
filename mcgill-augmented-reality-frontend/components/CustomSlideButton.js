import {Image} from 'react-native';
import React from 'react';
import SlideButton from 'rn-slide-button';

export default function CustomSlideButton() {
    const retrieveIFrame = () => {
        console.log("Retrieving IFrame...");
    }

    return (
        <SlideButton
            title='Visualize Path'
            borderRadius={6}
            height={50}
            onReachedToEnd={retrieveIFrame}
            containerStyle={{ backgroundColor: '#CD202C' }}
            thumbStyle={{ borderRadius: 6, width: 44 }}
            underlayStyle={{ backgroundColor: '#CD202C' }}
            titleStyle={{fontWeight: 'bold'}}
            icon={
                <Image
                    style={{ width: 24, height: 24, tintColor: '#CD202C' }}
                    source={require('../assets/arrow-right.png')}
                />
            }
        />
    );
}
