import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import '../css/header.css';

function Header() {
  return (
    <>
    <Navbar bg="dark" variant="dark">
    <Container>
      <Nav className="me-auto">
        <Nav.Link href="/" className='active'> Home </Nav.Link>
        <Nav.Link href="/Contact" className='active'> Your Profile </Nav.Link>
        <Nav.Link href="/People" className='active'> Your Friends </Nav.Link>
      </Nav>
    </Container>
  </Navbar>
  </>
  )
}

export default Header