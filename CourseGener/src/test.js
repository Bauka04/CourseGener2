import {useCookies} from 'react-cookie';

function test(){
    const [cookieJWT, setCookieJWT] = useCookies(['jwt']);

    console.log(cookieJWT['jwt']);
}
test();