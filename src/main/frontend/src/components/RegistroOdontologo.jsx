import InputForm from "./InputForm";
import Navbar from "./Navbar";
import style from '../styles/RegistroOdontologo.module.css'
import {Link, useHistory} from "react-router-dom";

export default function RegistroOdontologo(){
    const history = useHistory();

    const handlerSubmit = (evento) => {
        evento.preventDefault();

        let form = {
            nombre: document.querySelector("#Nombre").value,
            apellido: document.querySelector("#Apellido").value,
            matricula: document.querySelector("#Matricula").value,
        }
        console.log(form);

        let datos = {
            method: "POST",
            body: JSON.stringify(form),
            headers: {
                'Content-Type': 'application/json'
            }
        }

        fetch ("/odontologos/add", datos)
        .then((res) => res.json())
        .then((data) => {
            console.log("ok");
            evento.target.reset();
            alert("El odontologo se registró correctamente.")
            history.push("/odontologos");
        })
        .catch((error) => console.log(error))

    }

    return(
        <div className={style.bodyOdontologo}>
            <nav className={style.nav}>
                <div className={style.button}>
                <Link to="/"><Navbar dato="Home" class={style.NavButtonRO}/></Link>
                    <Link to="/pacientes"><Navbar dato="Pacientes"class={style.NavButtonRO}/></Link>
                    <Link to="/odontologos"><Navbar dato="Odontologos"class={style.NavButtonRO}/></Link>
                    <Link to="/turnos"><Navbar dato="Turnos" class={style.NavButtonRO}/></Link>
                </div>
                <h1 className={style.clident}>Cli-Dent</h1>
            </nav>
            <main className={style.main}>
                <article className={style.articleRO}>
                    <form action="/odontologos/add" onSubmit={handlerSubmit} method="POST" className={style.RegistroOdontologo}>
                        <h1 className={style.h1Registro}>Registro</h1>
                        <InputForm dato="Nombre" type="text" />
                        <InputForm dato="Apellido" type="text" />
                        <InputForm dato="Matricula" type="text" />

                        <input type="submit" value="Enviar" className={style.enviar}/>
                    </form>
                </article>
            </main>
        </div>
    )
}