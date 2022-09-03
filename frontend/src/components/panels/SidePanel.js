import { CategoryListPanel } from './category/CategoryListPanel'
import { TrendingListPanel } from './trending/TrendingListPanel'

export const SidePanel = () => {

    return (
        <>
        {/* <div className='container'> */}
            <TrendingListPanel />
            <CategoryListPanel />
            {/* </div> */}
        </>
    )
}