

export interface Page<T> { 
    content: T[];
    number: number;
    hasNext: boolean;
    totalPages: number;
    totalElements: number;
}