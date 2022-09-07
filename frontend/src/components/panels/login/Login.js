import React from 'react'
import Container from 'react-bootstrap/Container'
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Col from 'react-bootstrap/Col'
import Row from 'react-bootstrap/Row'

const [usernameReg, setUsernameReg] = useState("")
const [passwordReg, setPasswordReg] = useState("")


const Login = () => {
  return (
    <Container className="d-grid h-100">
      <Form className="text-center w-100">
        <h1 className="mb-3 fs-3 fw-normal">
          Please sign in
        </h1>
        <Form.Group className="mb-1"
          controlId="signInUsername-id">
          <Form.Control type="text" placeholder="Username" autoComplete="username" className="position-relative" onChange={(e) => {
            setUsernameReg(e.target.value)
          }}/>
        </Form.Group>
        <Form.Group className="mb-1"
        controlId="signInPassword-id">
          <Form.Control type="text" placeholder="Password:" className="position-relative" onChange={(e) => {
            setPasswordReg(e.target.value)
          }}/>
        </Form.Group>
        <div className="d-grid">
          <Button variant="primary" size='lg'>Sign in</Button>
        </div>
        <p className="mt-5">&copy; 2022-2023</p>
      </Form>
    </Container>
  )
}

export default Login