import * as crypto from 'crypto-js';
import Base64 from 'crypto-js/enc-base64';
import { BACKEND, USERNAME, PASSWORD } from '@env';

const base64Url = (str) => {
    return str.toString(Base64).replace(/=/g, '').replace(/\+/g, '-').replace(/\//g, '_');
}

const encodeBase64 = (str) => {
    let encodedWord = crypto.enc.Utf8.parse(str)
    return base64Url(crypto.enc.Base64.stringify(encodedWord));
}

async function getToken(username, password) {
    const base64Encoded = encodeBase64(`${username}:${password}`);
    try {
        const res = await fetch(`${BACKEND}/token`, { method: 'POST', headers: { Authorization: 'Basic ' + base64Encoded }});
        const { status, ok } = await res;
        if(!ok) {
            console.error(`Unable to get token. Status Code: ${status} Message: ${res.statusText}`);
            return;
        }
        return await res.text();
    } catch (err) {
        console.error("Something went wrong. Unable to get token. " + err);
        return;
    }
}

/**
 * Wrapper around fetch api that attaches user token to request.
 *
 * @param {*} endpoint - url
 * @param {*} payload - anything meant to be passed in the body
 * @param {*} headers - headers
 * @param {*} method - ["GET", "POST", "PUT", "DELETE"]
 */
export default async function fetchWrapper(
    endpoint,
    payload = null,
    headers = null,
    method = 'GET'
) {
    if (!['GET', 'POST', 'PUT', 'DELETE'].includes(method)) {
        console.error(
            'Invalid method provided. Methods available are: GET, POST, PUT, DELETE'
        );
        return;
    }
    headers = {...headers, Authorization: 'Bearer ' + await getToken(USERNAME, PASSWORD)};

    let options = {
        method,
        ...(payload && method !== 'GET' && { body: JSON.stringify(payload) }),
        ...(headers && { 'headers': headers })
    };
    try {
        const res = await fetch(endpoint, options);
        const { status, ok } = res;
        if(!ok) {
            console.error(`Bad request. Status Code: ${status} Message: ${res.statusText}`);
            return;
        }
        return await res.json();
    } catch (err) {
        console.error(`Unable to make ${method} request to ${endpoint} endpoint. ${err}`);
        return;
    }
}
