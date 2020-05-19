import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TypeCast} from './search-page.model';

@Injectable({
  providedIn: 'root'
})
export class SearchPageService {

  constructor(private http: HttpClient) {
  }

  getTypecast(searchInput): Observable<HttpResponse<TypeCast>> {
    return this.http.get<TypeCast>(`/api/search/typecast`, {
      params: {
        firstName: searchInput.firstName
      },
      observe: 'response'
    });
  }

  getCoincidence(searchInput): Observable<any> {
    return this.http.get<TypeCast>(`/api/search/coincidence`, {
      params: {
        firstName: searchInput.firstName,
        secondName: searchInput.secondName
      },
      observe: 'response'
    });
  }
}
