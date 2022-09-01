import { CategoryListPanel } from './category/CategoryListPanel'
import { TrendingListPanel } from './trending/TrendingListPanel'

export const SidePanel = () => {

    return (
        <>
            <TrendingListPanel />
            <CategoryListPanel />
        </>
    )
}