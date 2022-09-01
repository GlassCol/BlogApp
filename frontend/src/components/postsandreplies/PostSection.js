import React from 'react'
import { useApiResources } from '../../utils/useApiResources'

function PostSection() {
  const [posts, loading] = useApiResources("posts")

  return (
    <article className='card border border-0' >
      <dl className='card-body'>
      <dt className='card-title text-dark' > POST SECTION ... </dt>

        {loading ? <div> ... loading </div> :
            posts.map(post => {
              return (
                <div key={post.id}>
                  <dt><label>Title: </label> {post.title} </dt>
                  <dd><small className='border m-0 p-1' >postedBy: {post.userId}</small></dd>
                  <dd className='border-bottom'> {post.body} </dd>
                </div>
              )
            })
        }
      </dl>
    </article>
  )
}

export default PostSection