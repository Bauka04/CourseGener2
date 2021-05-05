import {useContext, useEffect, useState} from "react";
import UserContext from "./UserContext";
import {Switch, Route, BrowserRouter, Link} from "react-router-dom";
import ProfilePage from "./ProfilePage";
import Course from "./Course";
import Courses from "./Courses";
import axios from "axios";
import {useCookies} from "react-cookie";


function Profile() {
    const {user, login} = useContext(UserContext);
    const [cookies] = useCookies('jwt');
    const [isLoading, setLoading] = useState(true);
    const bearer = "Bearer" + cookies['jwt'];

    useEffect(() => {
        axios.get("http://localhost:8000/profile?auth="+bearer, {
            headers: {
                'content-type': 'application/json',
                'Authorization': bearer
            }
        }).then(response => {
            const data = response.data;
            login(data.id, data.email, data.fullName);
            setLoading(false);
        })
    },[bearer, login]);

    if (isLoading){
        return <div className='min-vh-100'>Loading...</div>
    }

    return (
        <BrowserRouter>
            <div className='bg-light min-vh-100'>
                <div className='row pt-3'>
                    <div className='col-3'>
                        <div className='card bg-dark'>
                            <div className='card-header text-light'>
                                <Link to='/profile'>{user.fullName}</Link>
                            </div>
                            <div className='card-body p-0'>
                                <div className='list-group'>
                                    <div className='list-group-item bg-dark'>
                                        <Link to='/schedules'>
                                            Schedules
                                        </Link>
                                    </div>
                                    <div className='list-group-item bg-dark'>
                                        <Link to='/settings'>Settings</Link>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className='col-9'>
                        <Switch>
                            <Route path='/profile'>
                                <ProfilePage/>
                            </Route>
                            <Route path='/schedules/:scheduleID'>
                                <Course/>
                            </Route>
                            <Route path='/schedules'>
                                <Courses/>
                            </Route>
                        </Switch>
                    </div>
                </div>
            </div>
        </BrowserRouter>
    );
}

export default Profile;