import {createContext} from "react";

const UserContext = createContext({email: '', fullName: '', auth: false});

export default UserContext;