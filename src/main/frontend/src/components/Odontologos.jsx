import React, { useState, useEffect } from "react";
import style from '../styles/Odontologos.module.css'
import Navbar from './Navbar';
import {Link, useHistory} from "react-router-dom";

export default function Odontologos(){
const [listaOdontologos, setLista] = useState([]);
const history = useHistory();

const listar = (metodo)=>{

    let datos ={
        method: metodo,
        headers:{
        'Content-Type': 'application/json'
        },
    };

    fetch("/odontologos", datos)
    .then((response)=>response.json())
    .then((data)=>{
        setLista(data);
        console.log(data);
    })
    .catch((error)=>{
        console.log(error);
    });
}

const handleUpdate = (odontologo) => {
    history.push({pathname: "/odontologos/update", state: {odontologo}})
}

useEffect(()=> {
        listar("GET");
}, []);

    
    return(
        <div className={style.bodyOdontologos}>
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
                <table className={style.tableO}>
                <tr className={style.titulos}>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Matrícula</th>
                    <th className={style.thVacio}></th>
                </tr>
                {(listaOdontologos.length == 0) ? "" : listaOdontologos.map((odontologo, index) => 
                <tr key={index} className={style.lista}>
                    <td>{odontologo.id}</td>
                    <td>{odontologo.nombre}</td>
                    <td>{odontologo.apellido}</td>
                    <td>{odontologo.matricula}</td>
                    <td><input id={index} type="button" value="Modificar" onClick={() => handleUpdate(odontologo)} className={style.buttonO} /></td>
                    {/* <td><input id={index} type="button" value="Borrar" onClick = {borrar} className={style.buttonP}/></td> */}
                </tr>
                )}
                </table>
                <div className={style.agregarO}>
                    <Link to="/odontologos/add"><input type="button" value="Agregar" className={style.agregarO}/></Link>
                </div>
            </main>
        </div>
    );
}