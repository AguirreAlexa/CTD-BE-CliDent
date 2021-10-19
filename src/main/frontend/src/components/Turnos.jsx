import React, { useState, useEffect } from "react";
import style from '../styles/Turnos.module.css'
import Navbar from './Navbar';
import {Link, useHistory} from "react-router-dom";

export default function Turnos(){
const [listaTurnos, setListaTurnos] = useState([]);

const listar = (metodo)=>{

    let datos ={
        method: metodo,
    };

    fetch("/turnos", datos)
    .then((response)=>response.json())
    .then((data)=>{
        console.log(data);
        setListaTurnos(data);
    })
    .catch((error)=>{
        console.log(error);
    });
}

const handleDelete = (turno) => {
    let id = turno.id;

    let datos ={
        method: "DELETE",
    };

    fetch("/turnos/delete/" + id, datos)
    .then((response)=>{
        console.log(response)
        return response})
    .then((data)=>{
        console.log(data);
        alert("Turno borrado correctamente");
        listar("GET");
    })
    .catch((error)=>{
        console.log(error);
    });
};

useEffect(()=> {
    listar("GET");
}, []);

    
    return(
        <div className={style.bodyTurnos}>
            <nav className={style.nav}>
                <div className={style.button}>
                    <Link to="/"><Navbar dato="Home" class={style.NavButton}/></Link>
                    <Link to="/pacientes"><Navbar dato="Pacientes"class={style.NavButton}/></Link>
                    <Link to="/odontologos"><Navbar dato="Odontologos"class={style.NavButton}/></Link>
                    <Link to="/turnos"><Navbar dato="Turnos" class={style.NavButton}/></Link>
                </div>
                <h1 className={style.clident}>Cli-Dent</h1>
            </nav>
            <main className={style.main}>
                <table className={style.tableT}>
                <tr className={style.titulos}>
                    <th>ID</th>
                    <th>Paciente</th>
                    <th>Odontologo</th>
                    <th>Fecha y Hora</th>
                    <th className={style.thVacio}></th>
                    <th className={style.thVacio}></th>
                </tr>
                {(listaTurnos.length == 0) ? "" : listaTurnos.map((turno, index) => 
                <tr key={index} className={style.lista}>
                    <td>{turno.id}</td>
                    <td>{turno.paciente.nombre} {turno.paciente.apellido}</td>
                    <td>{turno.odontologo.nombre} {turno.odontologo.apellido}</td>
                    <td>{turno.horario}</td>
                    <td><input id={index} type="button" value="Modificar" className={style.buttonT}/></td>
                    <td><input id={index} type="button" value="Borrar" onClick = {() => handleDelete(turno)} className={style.buttonT}/></td>
                </tr>
                )}
                </table>
                <div className={style.agregarT}>
                    <Link to="/turnos/add"><input type="button" value="Agregar" className={style.agregarT}/></Link>
                </div>
            </main>
        </div>
    );
}