import {useContext} from "react";
import UserContext from "./UserContext";

function ProfilePage(){
    const {user} = useContext(UserContext);

    return(
        <div className='bg-light m-2 ' id='tab'>
            <ul className="nav nav-tabs" id="myTab">
                <li className="nav-item">
                    <button className='nav-link' id='btn1' onClick={switchtab}>Schedule</button>
                </li>
                <li className="nav-item">
                    <button className='nav-link' id='btn2' onClick={switchtab2}>About</button>
                </li>
            </ul>
            <div className='p-4' id='tab1'>
                <h4 className='mx-auto'>Online Schedule Generator</h4>
                <p>Schedule Generator Online, S-GEN, is a free web
                    application, for creating weekly/daily schedules
                    for any activity (e.g. college, class, work and holiday).
                    It is designed with a focus on ease of use
                    and personalization.
                </p>
                <p>
                    Get started by simply adding activities directly to the schedule.
                    The timetable will automatically adjust its length to fit all
                    your activities perfectly. Personalize your schedule by customizing
                    any of the following options:
                </p>
                <ul>
                    <li>Background image / Background color</li>
                    <li>Text color</li>
                    <li>Description for every activity</li>
                    <li>Download the jpg format of your schedule</li>
                </ul>
                <p>If you have any questions or feedback, you can post them in the comment field down
                    below, or contact us privately here.</p>
            </div>
            <div id='tab2' className='p-4 d-none'>
                <div className='row'>
                    <div className='col-5'>
                        <h5>User ID:</h5>
                        <h5>Full Name:</h5>
                        <h5>Email:</h5>
                    </div>
                    <div className='col-5'>
                        <p className='mb-2'>{user.id}</p>
                        <p className='mb-2'>{user.fullName}</p>
                        <p>{user.email}</p>
                    </div>
                </div>
            </div>
        </div>
    )

    async function switchtab() {
        document.getElementById('tab1').className = 'p-4';
        document.getElementById('tab2').className = 'p-4 d-none';
        document.getElementById('btn1').className = 'nav-link active disabled';
        document.getElementById('btn2').className = 'nav-link';
    }

    async function switchtab2() {
        document.getElementById('tab2').className = 'p-4';
        document.getElementById('tab1').className = 'p-4 d-none';
        document.getElementById('btn2').className = 'nav-link active disabled';
        document.getElementById('btn1').className = 'nav-link';
    }
}
export default ProfilePage;