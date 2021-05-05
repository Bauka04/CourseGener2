import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {useCookies} from "react-cookie";
import axios from "axios";

function Courses() {
    const [title, setTitle] = useState("");
    const [bg_color, setBg_color] = useState("#ffffff");
    const [bg_image, setBg_image] = useState("");

    const [schedules, setSchedules] = useState(null);
    const [isLoading, setLoading] = useState(true);
    const [title1, setTitle1] = useState('Schedule');
    const [cookies] = useCookies('jwt');
    let token = cookies['jwt'];
    const bearer = "Bearer" + token;
    useEffect(() => {
        axios.get("http://localhost:8000/schedule/all?auth=" + bearer, {
            headers: {
                "Content-Type": "application/json",
                "Authorization": bearer
            }
        }).then(response => {
            setSchedules(response.data);
            setLoading(false);
            document.title = title1;
        })
    }, [bearer, title1]);
    if (isLoading) {
        return <div className='min-vh-100'>Loading...</div>
    }

    async function newSchedule(data) {
        const bearer = "Bearer" + token;
        axios.post("http://localhost:8000/schedule/add?auth=" + bearer, data, {
            headers: {
                'content-type': 'application/json',
                'Authorization': bearer
            }
        }).then(response => {
            closeForm();
            document.title = "Adding...";
            setTitle1('Schedule');
        });
    }

    const handleTitle = event => {
        setTitle(event.target.value);
    }
    const handleBgcolor = event => {
        setBg_color(event.target.value);
    }
    const handleBgimage = event => {
        setBg_image(URL.createObjectURL(event.target.files[0]));

    }

    const handleSubmit = event => {
        let id = "1";
        const inputData = {id, title, bg_color, bg_image};
        newSchedule(inputData);
        event.preventDefault();
    }

    async function openAddForm() {
        document.getElementById('add_form').className = '';
        document.getElementById('list1').className = 'd-none';
    }

    async function closeForm() {
        document.getElementById('add_form').className = 'd-none';
        document.getElementById('list1').className = '';
    }

    return (
        <div className='p-3'>
            <div className='col-8 mx-auto'>
                <div className='row mx-2'>
                    {(schedules.length < 1) ? <p className='h5'>There is no schedules</p> :
                        <p className='h5'>There are {schedules.length} schedules</p>}
                    <button className='btn btn-primary ml-auto' onClick={openAddForm}>ADD</button>
                </div>
                <hr/>
                <div id='list1'>
                    <ListSchedules schedules={schedules}/>
                </div>
                <form onSubmit={handleSubmit} id='add_form' className='d-none'>
                    <div className='row'>
                        <h4 className='col-8 mx-auto'>Create new schedule with original title</h4>
                        <img onClick={closeForm} alt="" className='mr-3'
                             src="https://img.icons8.com/pastel-glyph/30/000000/cancel--v1.png"/>
                    </div>
                    <div className='form-group'>
                        <input type="text" minLength={1} placeholder='Title of Course' className='form-control my-2'
                               value={title} onChange={handleTitle}/>
                        <label>Select background color or Image for your Course</label>
                        <input type="color" className='form-control my-2' value={bg_color}
                               onChange={handleBgcolor}/>
                        <input type="file" accept='image/jpeg, image/png' className='form-control-file'
                               onChange={handleBgimage}/>
                    </div>
                    <div className='mt-4'>
                        <img src={bg_image} alt="" className='w-100'/>
                    </div>
                    <button className='btn btn-outline-primary my-3'>Create Course</button>
                </form>

            </div>
        </div>
    )
}

function ListSchedules(props) {
    if (props.schedules != null) {
        const list = props.schedules.map((schedule, index) =>
            <li className='item' key={index}>
                <h2 className='headline'>
                    <Link to={'/schedules/' + schedule.title+'/'+schedule.id}>{schedule.title}</Link>
                </h2>
            </li>
        );
        return (
            <div>
                <ol className='list'>
                    {list}
                </ol>
            </div>
        )
    }
}

export default Courses;