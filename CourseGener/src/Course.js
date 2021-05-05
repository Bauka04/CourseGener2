import {useHistory, useLocation} from "react-router-dom";
import {useCookies} from "react-cookie";
import {useEffect, useState} from "react";
import axios from "axios";

function Course() {
    const [activities, setActivities] = useState(null);
    const [isLoading, setLoading] = useState(true);
    const [cookies] = useCookies('jwt');
    const location = useLocation();
    const history = useHistory();
    const scheduleTitle = location.pathname.split('/')[2];
    const id = location.pathname.split('/')[3];
    const bearer = "Bearer" + cookies['jwt'];
    useEffect(() => {
        axios.get("http://localhost:8000/activity/get/" + id + "?auth=" + bearer, {
            headers: {
                'content-type': 'application/json',
                'Authorization':bearer
            }
        }).then(response => {
                setActivities(response.data);
                setLoading(false);
            })
    },[bearer, id]);
    if (isLoading) {
        return <div>Loading...</div>
    }
    async function getRows(data){
        const rows = data.map((row)=>
            <tr style={'color: '+row.text_color}>
                <td>{row.days}</td>
                <td>{row.start_time}-{row.end_time}</td>
                <td>{row.name}</td>
                <td>{row.description}</td>
            </tr>
        )
        return rows;
    }
    return (
        <div>
            <div className='h3 text-center'>{scheduleTitle}</div>
            <table className='table table-striped' id='file'>
                <thead className='thead-dark'>
                <tr>
                    <th>Day</th>
                    <th>Start - End</th>
                    <th>Name</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                {getRows(activities)}
                </tbody>
            </table>
            <button onClick={deleteSchedule} className='btn btn-danger'>Delete Schedule</button>
            <button className='btn btn-dark' onClick={download}>Download</button>
            <button className='btn btn-dark' onClick={downloadPDF}>Download PDF</button>
        </div>
    )

    async function deleteSchedule() {
        axios.post("http://localhost:8000/schedule/delete/" + id + "?auth=" + bearer).then(response => {
            if (response.status === 200) {
                history.push('/schedules');
            }
        })
    }

    async function download(){
        return fetch('https://cloudlayer-io.p.rapidapi.com/v1/html/image',{
            method: 'POST',
            body: JSON.stringify({
                html: btoaStyle+btoa(document.getElementById('file').outerHTML),
                timeout: 30000
            }),
            headers: {
                "content-type": "application/json",
                "x-rapidapi-key": "3238066a34msh1a6aed6e99441c5p12f422jsnd0def21ebee6",
                "x-rapidapi-host": "cloudlayer-io.p.rapidapi.com"
            }
        }).then(response => response.blob())
            .then(blob => {
                var url = window.URL.createObjectURL(blob);
                var a = document.createElement('a');
                a.href = url;
                a.download = "schedule.jpg";
                document.body.appendChild(a);
                a.click();
                a.remove();
            });

    }

    async function downloadPDF(){
        return fetch('https://cloudlayer-io.p.rapidapi.com/v1/html/pdf',{
            method: 'POST',
            body: JSON.stringify({
                html: btoaStyle+btoa(document.getElementById('file').outerHTML),
                timeout: 30000
            }),
            headers: {
                "content-type": "application/json",
                "x-rapidapi-key": "3238066a34msh1a6aed6e99441c5p12f422jsnd0def21ebee6",
                "x-rapidapi-host": "cloudlayer-io.p.rapidapi.com"
            }
        }).then(response => response.blob())
            .then(blob => {
                var url = window.URL.createObjectURL(blob);
                var a = document.createElement('a');
                a.href = url;
                a.download = "schedule.pdf";
                document.body.appendChild(a);
                a.click();
                a.remove();
            });

    }
}

export default Course;