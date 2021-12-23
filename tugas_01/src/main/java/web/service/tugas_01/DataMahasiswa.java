/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web.service.tugas_01;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Nafi'
 */
@Entity
@Table(name = "data_mahasiswa")
@NamedQueries({
    @NamedQuery(name = "DataMahasiswa.findAll", query = "SELECT d FROM DataMahasiswa d"),
    @NamedQuery(name = "DataMahasiswa.findByNim", query = "SELECT d FROM DataMahasiswa d WHERE d.nim = :nim"),
    @NamedQuery(name = "DataMahasiswa.findByNama", query = "SELECT d FROM DataMahasiswa d WHERE d.nama = :nama"),
    @NamedQuery(name = "DataMahasiswa.findByAlamat", query = "SELECT d FROM DataMahasiswa d WHERE d.alamat = :alamat"),
    @NamedQuery(name = "DataMahasiswa.findByProdi", query = "SELECT d FROM DataMahasiswa d WHERE d.prodi = :prodi"),
    @NamedQuery(name = "DataMahasiswa.findByFakultas", query = "SELECT d FROM DataMahasiswa d WHERE d.fakultas = :fakultas")})
public class DataMahasiswa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nim")
    private Long nim;
    @Column(name = "nama")
    private String nama;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "Prodi")
    private String prodi;
    @Column(name = "fakultas")
    private String fakultas;

    public DataMahasiswa() {
    }

    public DataMahasiswa(Long nim) {
        this.nim = nim;
    }

    public Long getNim() {
        return nim;
    }

    public void setNim(Long nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nim != null ? nim.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataMahasiswa)) {
            return false;
        }
        DataMahasiswa other = (DataMahasiswa) object;
        if ((this.nim == null && other.nim != null) || (this.nim != null && !this.nim.equals(other.nim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.service.tugas_01.DataMahasiswa[ nim=" + nim + " ]";
    }
    
}
