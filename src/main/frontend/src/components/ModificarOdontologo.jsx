import InputForm from "./InputForm";
import Navbar from "./Navbar";
import style from '../styles/ModificarOdontologo.module.css';
import swal from 'sweetalert';
import React, { useState, useEffect } from "react";
import {Link, useHistory} from "react-router-dom";

export default function ModificarOdontologo(props){
    const { state } = props.location;
    const history = useHistory();
    const [form, setForm] = useState({ 
        nombre: state.odontologo.nombre,
        apellido: state.odontologo.apellido,
        matricula: state.odontologo. matricula,
    })

    useEffect(() => {
        document.querySelector("#Nombre").value = state.odontologo.nombre
        document.querySelector("#Apellido").value = state.odontologo.apellido
        document.querySelector("#Matricula").value = state.odontologo.matricula
    }, [])

    const handlerChange = (e) => {
        setForm({...form, [`${e.target.name}`]: e.target.value});
        console.log("Odontologo modificado");
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

        fetch ("/odontologos/update", datos)
        .then((response) => {
            console.log(response)})
        .then((data) => {
            console.log("ok");
            evento.target.reset();
            swal("", "Se registró correctamente", "success").then(function() {
                history.push("/odontologos");
            });
        })
        .catch((error) => console.log(error))
    }

    return(
        <div className={style.bodyModificar}>
            <nav className={style.nav}>
                <div className={style.button}>
                    <Link to="/odontologos"><Navbar dato="Atrás" class={style.NavButtonMO}/></Link>
                </div>
                <h1 className={style.clident}>Cli-Dent</h1>
            </nav>
            <main className={style.main}>
                <article className={style.articleMO}>
                    <form action="/odontologos/update" className={style.ModificarOdontologo} method="POST" onSubmit={handlerSubmit}>
                        <h1 className={style.h1Modificar}>Modificar</h1>
                        <InputForm dato="Nombre" name="nombre" type="text" event={handlerChange}/>
                        <InputForm dato="Apellido" name="apellido" type="text" event={handlerChange} />
                        <InputForm dato="Matricula" name="matricula" type="number" event={handlerChange} />

                        <input type="submit" value="Enviar" className={style.enviar} />
                    </form>
                </article>
            </main>
        </div>
    )
}