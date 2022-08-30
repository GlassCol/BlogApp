import React from 'react';

export const CategoryPane = ( {id, parentCategory, subCategory}) => {

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
                        Category {parentCategory}
                    </button>
                </dt>
                <dd id={ddId}
                    className="accordion-collapse collapse" 
                    aria-labelledby={dtId}
                    data-bs-parent="#accordionParent">

                    <ul className="accordion-body">
                        {subCategory.map((sub) => {
                            return <li key={sub.id}>Category {sub.caption}</li> 
                        })}
                    </ul>
                </dd>
            </dl>

        </>
    )
}

