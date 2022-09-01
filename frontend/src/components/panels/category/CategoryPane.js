import React from 'react';

export const CategoryPane = ( {id, label, childCategories}) => {

    const dtId = "heading-"+id;
    const ddId = "flush-"+id;
    const ddTargetId = "#flush-"+id;
    return (
        <>
            <dl className='accordion-item p-0 m-0' >
                <dt className='accordion-header' id={dtId}>
                    <button className="accordion-button collapsed p-1 m-1" 
                            type="button" 
                            data-bs-toggle="collapse" 
                            data-bs-target={ddTargetId}
                            aria-expanded="false" 
                            aria-controls={ddId}>
                        <small>{label}</small>
                    </button>
                </dt>
                <dd id={ddId}
                    className="accordion-collapse collapse" 
                    aria-labelledby={dtId}
                    data-bs-parent="#accordionParent">

                    <ul className="accordion-body m-0 p-2">
                        <ul className='nav flex-column'>
                            { childCategories.map((child) => {
                                return <li className='nav-item border-0' 
                                            key={child.id}>
                                            <small>{child.label}</small>
                                        </li> 
                            }) }
                        </ul>
                    </ul>
                </dd>
            </dl>

        </>
    )
}

