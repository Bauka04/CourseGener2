import {useState} from "react";
import UserContext from "./UserContext";
import {useCookies} from "react-cookie";
const UserProvider = ({children}) => {//eslint-disable-next-line
    const [cookies, setCookie, removeCookie] = useCookies(['jwt']);
    const [user, setUser] = useState({id: "", email: "", fullName: "", auth: false});
    const login = (id, email, fullName) => {
        setUser((user) => ({
            id: id,
            email: email,
            fullName: fullName,
            auth: true
        }));
    }

    const logout = () => {
        setUser((user) => ({
            id: '',
            email: '',
            fullName: '',
            auth: false
        }));
        removeCookie('jwt');
        window.location = "http://localhost:3000/";
    }

    return (
        <UserContext.Provider value={{user, login, logout}}>
            {children}
        </UserContext.Provider>
    );
}
export default UserProvider;