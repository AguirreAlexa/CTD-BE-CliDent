import React, { useState, useEffect } from "react";
import InputForm from "./InputForm";
import Navbar from "./Navbar";
import style from '../styles/RegistroTurnos.module.css'
import {Link, useHistory} from "react-router-dom";
import swal from "sweetalert";

export default function RegistroTurnos(){
    const history = useHistory();
    const [listaPacientes, setListaP] = useState([]);
    const [listaOdontologos, setListaO] = useState([]);

    const listarPacientes = ()=>{

        let datos ={
            method: "GET",
            headers:{
            'Content-Type': 'application/json'
            },
        };
    
        fetch("/pacientes", datos)
        .then((response)=>response.json())
        .then((data)=>{
            setListaP(data);
            console.log(data);
        })
        .catch((error)=>{
            console.log(error);
        });
    }

    const listarOdontologos = ()=>{

        let datos ={
            method: "GET",
            headers:{
            'Content-Type': 'application/json'
            },
        };
    
        fetch("/odontologos", datos)
        .then((response)=>response.json())
        .then((data)=>{
            setListaO(data);
            console.log(data);
        })
        .catch((error)=>{
            console.log(error);
        });
    }
    

    const handlerSubmit = (evento) => {
        evento.preventDefault();

        // let body = {
        //     matricula: document.querySelector("#selectOdontologo").value,
        //     dni: document.querySelector("#selectPaciente").value,
        //     horario: document.querySelector("#Fecha").value + "T" + document.querySelector("#Hora").value +':00',
        // }
        // console.log(body);

        const params = new URLSearchParams();
        params.append("matricula", document.querySelector("#selectOdontologo").value);
        params.append("dni", document.querySelector("#selectPaciente").value);
        params.append("horario", document.querySelector("#Fecha").value + "T" + document.querySelector("#Hora").value +':00',);

        console.log(params);

        let datos = {
            method: "POST",
            body: params,
        }

        fetch ("/turnos/add", datos)
        .then((res) => res)
        .then((data) => {
            console.log("ok");
            evento.target.reset();
            swal("", "Se registró correctamente", "success").then(function() {
                history.push("/turnos");
            });
        })
        .catch((error) => console.log(error))

    }

    useEffect(()=> {
        listarPacientes();
        listarOdontologos();
    }, []);

    return(
        <div className={style.bodyTurnos}>
            <nav className={style.nav}>
                <div className={style.button}>
                <Link to="/"><Navbar dato="Home" class={style.NavButtonRT}/></Link>
                    <Link to="/pacientes"><Navbar dato="Pacientes"class={style.NavButtonRT}/></Link>
                    <Link to="/odontologos"><Navbar dato="Odontologos"class={style.NavButtonRT}/></Link>
                    <Link to="/turnos"><Navbar dato="Turnos" class={style.NavButtonRT}/></Link>
                </div>
                <h1 className={style.clident}>Cli-Dent</h1>
            </nav>
            <main className={style.main}>
                <article className={style.articleRT}>
                    <form action="/turnos/add" onSubmit={handlerSubmit} method="POST" className={style.RegistroTurnos}>
                        <h1 className={style.h1Registro}>Registro</h1>
                        <label for="selectOdontologo">Odontologo
                        <br />
                        <select name="selectOdontologo" id="selectOdontologo">
                            {(listaOdontologos.length == 0) ? "" : listaOdontologos.map((odontologo, index) => 
                                <option key={index} value={odontologo.matricula}>{odontologo.nombre} {odontologo.apellido}</option>
                            )}
                        </select>
                        </label>
                        <label for="selectPaciente">Paciente
                        <br />
                        <select name="selectPaciente" id="selectPaciente">
                            {(listaPacientes.length == 0) ? "" : listaPacientes.map((paciente, index) => 
                                <option key={index} value={paciente.dni}>{paciente.nombre} {paciente.apellido}</option>
                            )}
                        </select>
                        </label>
                        <InputForm dato="Fecha" type="date" tag="horario"/>
                        <InputForm dato="Hora" type="time" tag="horario"/>

                        <input type="submit" value="Enviar" className={style.enviar}/>
                    </form>
                </article>
            </main>
        </div>
    )
}