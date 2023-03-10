import interpolate from "spherical-geometry-js/src/interpolate";

/**
 * get list of nodes to a distation from origin and destination
 * @param {*} origin object with longitude and latitude
 * @param {*} destination object with longitude and latitude
 */


export async function getPath(origin, destination) {
    const distanceBetweenNodes = 0.7;
    let numberOfSteps;
    const nodes = [];

    const res = await fetch(`https://maps.googleapis.com/maps/api/directions/json?key=AIzaSyDfvmLoVDttb7GFLrC3Z_0ssLjEhtlkxF0&origin=${origin}&destination=${destination}&mode=walking`);
    const data = await res.json();
    numberOfSteps = data.routes[0].legs[0].steps.length;
    for (let i = 0; i < numberOfSteps; i++) {
        let flag = true;
        let step = data.routes[0].legs[0].steps[i];
        let numberOfNodes = Math.floor(step.distance.value / distanceBetweenNodes);
        if (step.distance.value % 0.7 === 0) flag = false;
        let distanceCoveredInFraction = 0;
        for (let j = 0; j < numberOfNodes; j++) {
            const node = interpolate(step.start_location, step.end_location, distanceCoveredInFraction);
            nodes.push(node);
            distanceCoveredInFraction += 0.7 / step.distance.value;
        }
        if (flag && i === numberOfSteps - 1) nodes.push(step.end_location);
    }
    return nodes;
}

export default getPath;