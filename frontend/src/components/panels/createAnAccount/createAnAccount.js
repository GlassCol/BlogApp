import React from 'react'
import Form from 'react-bootstrap/Form'
import Container from 'react-bootstrap/Container'

const createAnAccount = () => {
  return (
    <Container className="d-grid h-100">
        <Form className="text-center w-100n">
            <h1 className="mb-3 fs-3 fw-normal">Create An Account</h1>
            <Form.Group className="mb-1"
                controlId="createFirstName">
                <Form.Control type="text" placeholder="First Name" className="position-relative" />
            </Form.Group>
            <Form.Group className="mb-1" 
                controlId="createLastName">
                <Form.Control type="text" placeholder="Last Name" className="position-relative" />
            </Form.Group>
            <Form.Group className="mb-1"
                controlId="createUsername-id">
                <Form.Control type="text" placeholder="Create Username" className="porition-relative" />
            </Form.Group>

        </Form>
    </Container>
  )
}

export default createAnAccount