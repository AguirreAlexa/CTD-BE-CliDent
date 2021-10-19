import React, { useState, useEffect } from "react";
import style from '../styles/Pacientes.module.css'
import Navbar from './Navbar';
import {Link, useHistory} from "react-router-dom";


export default function Pacientes(){
const [listaPacientes, setLista] = useState([]);
const history = useHistory();

const listar = (metodo)=>{

    let datos ={
        method: metodo,
    };

    fetch("/pacientes", datos)
    .then((response)=>response.json())
    .then((data)=>{
        setLista(data);
        console.log(data);
    })
    .catch((error)=>{
        console.log(error);
    });
}

const handleUpdate = (index) => {
    history.push({pathname: "/pacientes/update", state: {paciente: listaPacientes[index]}})
}


const handleDelete = (evento) => {
    let dni = listaPacientes[evento.target.id].dni;

    let datos ={
        method: "DELETE",
    };

    fetch("/pacientes/delete/" + dni, datos)
    .then((response)=>{
        console.log(response)
        return response})
    .then((data)=>{
        console.log(data);
        alert("Paciente borrado correctamente");
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
        <div className={style.bodyPacientes}>
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
                <table className={style.tableP}>
                <tr className={style.titulos}>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>DNI</th>
                    <th>Domicilio</th>
                    <th>Usuario</th>
                    <th className={style.thVacio}></th>
                    <th className={style.thVacio}></th>
                </tr>
                {(listaPacientes.length == 0) ? "" : listaPacientes.map((paciente, index) => 
                <tr key={index} className={style.lista}>
                    <td>{paciente.id}</td>
                    <td>{paciente.nombre}</td>
                    <td>{paciente.apellido}</td>
                    <td>{paciente.dni}</td>
                    <td>{paciente.domicilio}</td>
                    <td>{paciente.user.usuario}</td>
                    <td><input id={index} type="button" value="Modificar" onClick={() => handleUpdate(index)} className={style.buttonP}/></td>
                    <td><input id={index} type="button" value="Borrar" onClick = {handleDelete} className={style.buttonP}/></td>
                </tr>
                )}
                </table>
            </main>
        </div>
    );
}