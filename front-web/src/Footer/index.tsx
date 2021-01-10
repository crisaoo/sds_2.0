import './styles.css';
import {ReactComponent as InstagramIcon} from './instagram.svg';
import {ReactComponent as LinkedinIcon} from './linkedin.svg';
import {ReactComponent as GithubIcon} from './github.svg';

function Footer () {
    return (
        <footer className="main-footer">
            App desenvolvido durante a 2ª ed. do evento Semana DevSuperior

            <div className="footer-icons">
                <a href="https://www.linkedin.com/in/cristiano-costa-709469203/" target="_new">
                    <LinkedinIcon />
                </a>
                <a href="https://www.instagram.com/crisaocost" target="_new">
                    <InstagramIcon />
                </a>
                <a href="https://www.github.com/crisaoo" target="_new">
                    <GithubIcon />
                </a>
            </div>
        </footer>
    )
}

export default Footer;