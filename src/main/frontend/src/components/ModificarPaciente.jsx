import InputForm from "./InputForm";
import Navbar from "./Navbar";
import style from '../styles/ModificarPaciente.module.css';
import swal from 'sweetalert';
import React, { useState, useEffect } from "react";
import {Link, useHistory} from "react-router-dom";

export default function ModificarPaciente(props){
    const { state } = props.location;
    const history = useHistory();
    const [form, setForm] = useState({ 
        nombre: state.paciente.nombre,
        apellido: state.paciente.apellido,
        dni: state.paciente.dni,
        domicilio: state.paciente.domicilio
    })

    useEffect(() => {
        document.querySelector("#Nombre").value = state.paciente.nombre
        document.querySelector("#Apellido").value = state.paciente.apellido
        document.querySelector("#Dni").value = state.paciente.dni
        document.querySelector("#Domicilio").value = state.paciente.domicilio
    }, [])

    const handlerChange = (e) => {
        setForm({...form, [`${e.target.name}`]: e.target.value});
        console.log("Paciente modificado");
    }

    const handlerSubmit = (evento) => {
        evento.preventDefault();

        console.log(form);

        let datos = {
            method: "PUT",
            body: JSON.stringify(form),
            headers: {
                'Content-Type': 'application/json'
            }
        }

        fetch ("/pacientes/update", datos)
        .then((response) => {
            console.log(response)})
        .then((data) => {
            console.log("ok");
            evento.target.reset();
            swal("", "Se registró correctamente", "success").then(function() {
                history.push("/pacientes");
            });
        })
        .catch((error) => console.log(error))
    }

    return(
        <div className={style.bodyModificar}>
            <nav className={style.nav}>
                <div className={style.button}>
                    <Link to="/pacientes"><Navbar dato="Atrás" class={style.NavButtonMP}/></Link>
                </div>
                <h1 className={style.clident}>Cli-Dent</h1>
            </nav>
            <main className={style.main}>
                <article className={style.articleMP}>
                    <form action="/pacientes/update" className={style.ModificarPaciente} method="POST" onSubmit={handlerSubmit}>
                        <h1 className={style.h1Modificar}>Modificar</h1>
                        <InputForm dato="Nombre" name="nombre" type="text" event={handlerChange}/>
                        <InputForm dato="Apellido" name="apellido" type="text" event={handlerChange} />
                        <InputForm dato="Dni" name="dni" type="number" event={handlerChange} />
                        <InputForm dato="Domicilio" name="domicilio" type="text" event={handlerChange} />

                        <input type="submit" value="Enviar" className={style.enviar} />
                    </form>
                </article>
            </main>
        </div>
    )
}