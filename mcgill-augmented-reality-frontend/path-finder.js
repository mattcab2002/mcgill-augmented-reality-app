

/**
 * get list of nodes to a distation from origin and destination
 * @param {*} origin object with longitude and latitude
 * @param {*} destination object with longitude and latitude
 */


export function getPath(origin, destination) {
    const og = '45.471165, -73.650087';
    const dc = '45.471249, -73.648020';
    const distanceBetweenNodes = 0.7;
    let numberOfSteps;
    const nodes = [];

    fetch(`https://maps.googleapis.com/maps/api/directions/json?key=AIzaSyDfvmLoVDttb7GFLrC3Z_0ssLjEhtlkxF0&origin=${og}&destination=${dc}&mode=walking`)
    .then(res => res.json())
        .then(data => {
            numberOfSteps = data.routes[0].legs[0].steps.length;
            for(let i = 0; i < numberOfSteps; i++){
                let flag = true;
                let step = data.routes[0].legs[0].steps[i];
                let numberOfNodes = Math.floor(step.distance.value/0.7);
                if(step.distance.value % 0.7 === 0) flag = false;
                console.log(numberOfNodes);
                let distanceCoveredInFraction = 0;
                for(let j = 0; j < numberOfNodes; j++){
                    const node = google.maps.geometry.spherical.interpolate(step.starting_location, step.end_location, distanceCoveredInFraction);
                    nodes.push(node);
                    distanceCoveredInFraction = (distanceCoveredInFraction+0.7)/step.distance.value; 
                }
                if(flag && i === numberOfSteps-1) nodes.push(step.end_location);       
            }
            for(let k = 0; k < nodes.length; k++){
                console.log(nodes[k]);
            }
            return nodes;
        })
    .catch(err => console.error(err));
    
}

export default getPath;