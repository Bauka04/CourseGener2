import logo from "./images/logo.svg";
import {Link} from "react-router-dom";
import {useContext} from "react";
import UserContext from "./UserContext";

function Header() {
    const {user, logout} = useContext(UserContext);

    return (
        <div className='bg-dark py-3 row px-3 align-items-center h5 mb-0'>
            <img src={logo} alt="" width='50' height='50' className='ml-3'></img>
            <Link to="/">Schedule <span className='text-danger'>generator</span></Link>
            {(!user.auth)?
                <Link to="/register" className='btn btn-primary ml-auto'>Sign up</Link> :
                <Link to="/profile" className='ml-auto'>{user.fullName}</Link>}
            {(!user.auth)?
            <Link to="/login" className='mx-3 '>Log in</Link> :
                <button className='mx-3 btn btn-primary' onClick={logout}>Log out</button>}
        </div>
    );
}
export default Header;