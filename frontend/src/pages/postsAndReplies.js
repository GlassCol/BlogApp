import React from 'react'
import PostSection from '../components/postsandreplies/PostSection'
import Header from '../components/Header'
import Footer from '../components/Footer'
import { Banner } from '../components/banner/Banner'
import { SidePanel } from '../components/panels/SidePanel'
import Login from '../components/panels/login/Login'

const PostsAndReplies = () => {
  return (
    <>
      <Header />
        <section className='row'>

          <section className='col col-12'>
            <Banner />
          </section>

          <div className='container'>
            <div className='row'>
              <article className='col col-8'>
                <PostSection />
              </article>
              
              <article className='col col-4'>
                <SidePanel />
              </article>
            </div>
          </div>

        </section>
      <Login />
      <Footer />
    </>
        )
}

export default PostsAndReplies