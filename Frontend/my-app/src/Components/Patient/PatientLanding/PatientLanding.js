import imgmain from '../../../Resources/login-hero.svg';
import userIcon from '../../../Resources/UserIcon.png';
import passwordIcon from '../../../Resources/PasswordIcon.png';
import imgside from '../../../Resources/AppLogo.png';
import React, { useState } from 'react';
import logout from '../../../Resources/log-out.png';
import './PatientLanding.css'
const PatientLanding = () => {
  const [searchQuery, setSearchQuery] = useState('');

  const handleSearch = (event) => {
    setSearchQuery(event.target.value);
  };
  const handleLogout = () => {
    alert('Logout successful!');
  };
  return (
    <div class="Had-login-container">
      <div class="Patient-Login-hor">
        <div>
          <img src={imgside} id="patientsideimg" />
        </div>
        <div class="Search">  
           <input className="PatientSearch" type="text" placeholder="Search..." value={searchQuery} onChange={handleSearch}/>
        </div>
        <div class="PatientLogout" onClick={handleLogout}>  
        <img src={logout} alt="Logout" className="input-icon1" />
        </div>
    </div>
    <div></div>
      <div class="Patientfooter">
        <h2>About Us</h2>
      </div>
    </div>
    
  );
};

export default PatientLanding;
