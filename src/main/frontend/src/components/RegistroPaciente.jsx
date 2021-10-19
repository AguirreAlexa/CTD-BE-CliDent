import InputForm from "./InputForm";
import Navbar from "./Navbar";
import style from '../styles/RegistroPaciente.module.css';
import swal from 'sweetalert';
import {Link, useHistory} from "react-router-dom";

export default function RegistroPaciente(){
    const history = useHistory();

    const handlerSubmit = (evento) => {
        evento.preventDefault();

        let form = {
            nombre: document.querySelector("#Nombre").value,
            apellido: document.querySelector("#Apellido").value,
            dni: document.querySelector("#Dni").value,
            domicilio: document.querySelector("#Domicilio").value,
            usuario: document.querySelector("#Usuario").value,
            password: document.querySelector("#Password").value
        }
        console.log(form);

        let datos = {
            method: "POST",
            body: JSON.stringify(form),
            headers: {
                'Content-Type': 'application/json'
            }
        }

        fetch ("/pacientes/add", datos)
        .then((res) => res.json())
        .then((data) => {
            console.log("ok");
            evento.target.reset();
            swal("Bienvenido!", "Se registró correctamente", "success").then(function() {
                history.push("/login");
            });
        })
        .catch((error) => console.log(error))
    }

    return(
        <div className={style.bodyPaciente}>
            <nav className={style.nav}>
                <div className={style.button}>
                    <Link to="/"><Navbar dato="Home" class={style.NavButtonRP}/></Link>
                    <Link to="/about"><Navbar dato="About"class={style.NavButtonRP}/></Link>
                    <Link to="/login"><Navbar dato="Login"class={style.NavButtonRP}/></Link>
                    <Link to="/contact"><Navbar dato="Contact" class={style.NavButtonRP}/></Link>
                </div>
                <h1 className={style.clident}>Cli-Dent</h1>
            </nav>
            <main className={style.main}>
                <article className={style.articleRP}>
                    <form action="/pacientes/add" className={style.RegistroPaciente} method="POST" onSubmit={handlerSubmit}>
                        <h1 className={style.h1Registro}>Registro</h1>
                        <InputForm dato="Nombre" type="text" />
                        <InputForm dato="Apellido" type="text" />
                        <InputForm dato="Dni" type="number" />
                        <InputForm dato="Domicilio" type="text" />
                        <InputForm dato="Usuario" type="text" />
                        <InputForm dato="Password" type="password" />

                        <input type="submit" value="Enviar" className={style.enviar} />
                    </form>
                </article>
            </main>
        </div>
    )
}