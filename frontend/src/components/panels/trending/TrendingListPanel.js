import React from 'react';
import { useApiResources } from '../../../utils/useApiResources';


export const TrendingListPanel = () => {
    const [trending, loading] = useApiResources("categories/trending");

    return (
        <>
            <article className='card border border-0 '>
                <dl className='card-body border-bottom border-dark pb-1 mb-0'>
                    <dt className='card-title text-dark' > TRENDING ...</dt>
                    <ul className='nav d-flex flex-wrap justify-content-evenly '>
                        {loading ? <div> ... loading </div> :
                          trending.map(trend => {
                                return <li key={trend.id} className='nav-item border m-1 p-1'>
                                            <small>{trend.label}</small>
                                        </li>
                            })
                        }
                    </ul>
                </dl>
            </article>
        </>
    )
}